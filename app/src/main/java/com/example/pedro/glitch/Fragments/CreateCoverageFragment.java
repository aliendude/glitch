package com.example.pedro.glitch.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.pedro.glitch.Data.CoverageAsyncTask;
import com.example.pedro.glitch.R;
import com.example.pedro.glitch.SelectMapActivity;
import com.example.pedro.myapplication.backend1.coverages.model.Coverage;


public class CreateCoverageFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


    public static CreateCoverageFragment newInstance() {
        CreateCoverageFragment fragment = new CreateCoverageFragment();

        return fragment;
    }

    public CreateCoverageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_create_map, container, false);
        Button buttonSelectLocation = (Button)view.findViewById(R.id.buttonSelectLocation);
        buttonSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onButtonSelectLocationPressed(v);
            }
        });
        Button buttonCreateMap = (Button) view.findViewById(R.id.buttonCreateMap);
        buttonCreateMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onButtonCreateMapPressed(v);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            String lat = data.getStringExtra("lat");
            String lng = data.getStringExtra("lng");

            TextView labelLat=(TextView)getActivity().findViewById(R.id.labelLat);
            labelLat.setText(lat);
            TextView labelLng=(TextView)getActivity().findViewById(R.id.labelLng);
            labelLng.setText(lng);
        }catch(Exception e){}

    }
    public void onButtonSelectLocationPressed(View v)
    {
        Intent intent = new Intent(getActivity(), SelectMapActivity.class);
        startActivityForResult(intent, 0);
    }
    public void onButtonCreateMapPressed(View v)
    {
        EditText nameEt= (EditText)getActivity().findViewById(R.id.editText);
        String name= nameEt.getText()+"";
        EditText startTimeEt= (EditText)getActivity().findViewById(R.id.editText2);
        String startTime=startTimeEt.getText()+"";
        EditText endTimeEt= (EditText)getActivity().findViewById(R.id.editText3);
        String endTime=endTimeEt.getText()+"";
        TextView labelLat=(TextView)getActivity().findViewById(R.id.labelLat);
        String latlng=labelLat.getText()+"";
        TextView labelLng=(TextView)getActivity().findViewById(R.id.labelLng);
        latlng+=" "+labelLng.getText();
        EditText nOfParticipantsEt= (EditText)getActivity().findViewById(R.id.editText4);
        String nOfParticipants=nOfParticipantsEt.getText()+"";
        EditText descEt= (EditText)getActivity().findViewById(R.id.editText5  );
        String desc=descEt.getText()+"";

        String[] strArr= new String[2];
        strArr[0]="createMapMarker";
        Coverage m = new Coverage();
        m.setName(name);
        m.setStart(startTime);
        m.setEnd(endTime);
        m.setLocation(latlng);
        m.setNparticipants(nOfParticipants);
        m.setDescription(desc);
        CoverageAsyncTask callbackend1 = new CoverageAsyncTask(getActivity());
        callbackend1.execute(new Pair<String[],Coverage>(strArr,m));
        getActivity().finish();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
