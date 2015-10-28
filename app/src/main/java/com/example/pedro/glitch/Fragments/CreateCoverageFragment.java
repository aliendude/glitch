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


import com.example.pedro.glitch.Data.CreateCoverageAsyncTask;
import com.example.pedro.glitch.R;
import com.example.pedro.glitch.SelectMapActivity;
import com.example.pedro.glitch.SessionManager;
import com.example.pedro.myapplication.backend1.coverages.model.Coverage;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        View view=inflater.inflate(R.layout.fragment_create_coverage, container, false);
        Button buttonSelectLocation = (Button)view.findViewById(R.id.buttonSelectLocation);
        buttonSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onButtonSelectLocationPressed(v);
            }
        });
        Button buttonCreateMap = (Button) view.findViewById(R.id.buttonCreateCoverage);
        buttonCreateMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onButtonCreateCoveragePressed(v);
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
    public void onButtonCreateCoveragePressed(View v)
    {
        TextView labelLat = (TextView) getActivity().findViewById(R.id.labelLat);
        String latlng = labelLat.getText() + "";
        TextView labelLng = (TextView) getActivity().findViewById(R.id.labelLng);
        latlng += " " + labelLng.getText();
        EditText descEt = (EditText) getActivity().findViewById(R.id.editText5);
        String desc = descEt.getText() + "";
        EditText hashTagEt = (EditText) getActivity().findViewById(R.id.hashTagEditText);
        String hashTag = hashTagEt.getText() + "";
        SessionManager sessionManager = new SessionManager(getActivity().getApplicationContext());
        String str = sessionManager.getUserDetails().get("username");
        Coverage m = new Coverage();

        m.setLocation(latlng);
        m.setDateCreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        m.setNshared(0);
        m.setNshared(0);
        m.setHashtag(hashTag);
        m.setDescription(desc);
        // m.setCreator(Globals.getLoggedUser());
        CreateCoverageAsyncTask callbackend1 = new CreateCoverageAsyncTask(getActivity());
        callbackend1.execute(new Pair<String, Coverage>(str, m));
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
