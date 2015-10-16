package com.example.pedro.glitch;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;


public class MainActivity extends ActionBarActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(Globals.loggedUser==null){
            //start login activity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivityForResult(intent, 0);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("pedro", Globals.loggedUser.getName());
        Intent intent = new Intent(MainActivity.this, LoggedUserActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }






}

