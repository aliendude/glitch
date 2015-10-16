package com.example.pedro.glitch;


import android.net.Uri;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.example.pedro.glitch.Fragments.SelectMapFragment;


public class SelectMapActivity extends ActionBarActivity implements SelectMapFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_map);
        SelectMapFragment newFragment = new SelectMapFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_container,(Fragment)newFragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
