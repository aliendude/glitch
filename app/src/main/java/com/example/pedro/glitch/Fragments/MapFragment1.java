package com.example.pedro.glitch.Fragments;

import android.app.Activity;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedro.glitch.Constants;
import com.example.pedro.glitch.Globals;
import com.example.pedro.glitch.R;
import com.example.pedro.myapplication.backend1.coverages.Coverages;
import com.example.pedro.myapplication.backend1.coverages.model.Coverage;
import com.example.pedro.myapplication.backend1.coverages.model.CoverageCollection;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class MapFragment1 extends Fragment{

    MapView mMapView;
    private GoogleMap googleMap;


    private OnFragmentInteractionListener mListener;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private double mLongitude;
    private double mLatitude;
    private static View view;
    private Socket mSocket;
    private int numUsers;
    private String mUsername;
    private Map<String, Marker> usersMarkersMap =new HashMap<>();
    {
        try {
            //socket to handle the calls to the location tracking server
            mSocket = IO.socket(Constants.LOC_TRACKING_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public static MapFragment1 newInstance(String param1, String param2) {
        MapFragment1 fragment = new MapFragment1();
        return fragment;
    }
    public void onSelLocPressed(){

        Log.e("pedro", "pressed");
    }
    public MapFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.on("new message", onNewMessage);
        mSocket.on("user joined", onUserJoined);
        mSocket.on("location_changed", onLocationChanged);
        mSocket.on("user left", onUserLeft);
        mSocket.on("login", onLogin);
        mSocket.connect();
        mUsername = Globals.loggedUser.getUsername();
        getLocation();
        mSocket.emit("add user", mUsername,mLongitude+","+mLatitude);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off("new mesage", onNewMessage);
        mSocket.off("location_changed",onLocationChanged);
        mSocket.off("user joined", onUserJoined);
        mSocket.off("user left", onUserLeft);
        mSocket.off("login", onLogin);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.e("pedro","created");

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view =  inflater.inflate(R.layout.fragment_map, container, false);
            mMapView = (MapView) view.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume();// needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            googleMap = mMapView.getMap();
            new CoverageAsyncRetriever().execute();
            //æ geolocator example...
            //Geocoder geocoder = new Geocoder(getActivity());
            //List address =geocoder.getFromLocationName("1001 Manitou Ave Manitou Springs, CO 80829", 1);
            //Address location = (Address)address.get(0);
            //location.getLatitude();
            //location.getLongitude();


            //adding user's location marker
            try {
                getLocation();
                // user's location:
                MarkerOptions marker = new MarkerOptions().position(new LatLng(mLatitude, mLongitude)).title("You");
                // Changing marker icon
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fa_user));
                // adding marker
                googleMap.addMarker(marker);

            }catch (Exception e){
                Log.e("pedro","could not get your location");
            }


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(mLatitude, -mLatitude)).zoom(2).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            Button buttonGoToMyLocationPressed = (Button) view.findViewById(R.id.button_go_to_my_location);
            buttonGoToMyLocationPressed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onGoToMyLocationPressed(v);
                }
            });

        } catch (InflateException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(numUsers!=0)addParticipantsLog(numUsers);
    }
    public void onGoToMyLocationPressed(View view) {
        getLocation();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(mLatitude,mLongitude)).zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void addParticipantsLog(int numUsers){
        TextView nParticipantsText= (TextView) view.findViewById(R.id.location_tracking_nusers_text);
        nParticipantsText.setText("Users online: "+numUsers);
        //Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    private void addLog(String log){
        Toast.makeText(getActivity(), log, Toast.LENGTH_LONG).show();
    }
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(),
                            R.string.error_connect2, Toast.LENGTH_LONG).show();
                }
            });
        }
    };
    private Emitter.Listener onLocationChanged = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String location;
                    try {
                        username = data.getString("username");
                        location = data.getString("location");

                    } catch (JSONException e) {
                        return;
                    }

                }
            });
        }

    };
    private Emitter.Listener onLogin = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        numUsers = data.getInt("numUsers");

                    } catch (JSONException e) {
                        return;
                    }
                    Log.e("pedro",getResources().getString(R.string.message_welcome));
                    addParticipantsLog(numUsers);
                }
            });
        }

    };
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
                    // addMessage(username, message);
                }
            });
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String location, latitude,longitude;
                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                        location = data.getString("location").trim();
                        latitude= location.split(",")[0];
                        longitude= location.split(",")[1];
                    } catch (JSONException e) {
                        return;
                    }
                    if(usersMarkersMap.containsKey(username)){
                        Marker usermarker=usersMarkersMap.get(username);
                        usermarker.remove();
                        usersMarkersMap.remove(username);

                        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude),Double.parseDouble( longitude))).title(username);
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fa_user));
                        Marker marker= googleMap.addMarker(markerOptions);
                        usersMarkersMap.put(username,marker);
                        //usersMarkersMap.(username)
                    }else{
                        // create marker
                        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude),Double.parseDouble( longitude))).title(username);
                        // Changing marker icon
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fa_user));
                        // adding marker
                        Log.e("pedro","marker added");
                        Log.e("lat",latitude);
                        Log.e("lon",longitude);
                        Marker marker= googleMap.addMarker(markerOptions);
                        usersMarkersMap.put(username,marker);
                    }
                    addLog(getResources().getString(R.string.message_user_joined, username));
                    addParticipantsLog(numUsers);
                }
            });
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;

                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                    } catch (JSONException e) {
                        return;
                    }

                    addLog(getResources().getString(R.string.message_user_left, username));
                    addParticipantsLog(numUsers);

                }
            });
        }
    };

    private void getLocation() {
        // Get the location manager
        LocationManager locationManager = (LocationManager)
                getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        final int[] locationChangedCounter = {0};
        LocationListener loc_listener = new LocationListener() {

            public void onLocationChanged(Location l) {
                locationChangedCounter[0]++;
                if(locationChangedCounter[0]>10)
                {
                    locationChangedCounter[0]=0;
                    mSocket.emit("location changed", l.getLatitude()+"," +l.getLongitude());
                }
                //Log.e("pedro","location_changed");
            }
            public void onProviderEnabled(String p) {}
            public void onProviderDisabled(String p) {}
            public void onStatusChanged(String p, int status, Bundle extras) {}
        };
        locationManager
                .requestLocationUpdates(bestProvider, 0, 0, loc_listener);
        location = locationManager.getLastKnownLocation(bestProvider);
        try {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
        } catch (NullPointerException e) {
            mLatitude = -1.0;
            mLongitude = -1.0;
        }
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }


    private class CoverageAsyncRetriever extends AsyncTask<Void, Void, CoverageCollection>
    {

        public CoverageAsyncRetriever() {

        }
        private Coverages mapMarkersService = null;
        @Override
        protected CoverageCollection doInBackground(Void... params) {
            if (mapMarkersService == null) {
                Coverages.Builder builder = new Coverages.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // Need setRootUrl and setGoogleClientRequestInitializer only for local testing,
                        // otherwise they can be skipped
                        .setRootUrl(Constants.APPENGINE_URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                    throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end of optional local run code

                mapMarkersService = builder.build();
            }
            try {
                return  mapMarkersService.getCoverages().execute();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final CoverageCollection result) {

            try{

                for (Coverage element : result.getItems()) {
                    double longitude = Double.parseDouble(element.getLocation().split(" ")[1]);
                    double latitude = Double.parseDouble(element.getLocation().split(" ")[0]);
                    // create marker
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude)).title(element.getDescription());
                    // Changing marker icon
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_fa_arrow_down));
                    // adding marker
                    Marker marker= googleMap.addMarker(markerOptions);

                }
            } catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }



}