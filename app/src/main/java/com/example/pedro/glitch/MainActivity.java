package com.example.pedro.glitch;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;


public class MainActivity extends ActionBarActivity
{

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());
        if(!sessionManager.isLoggedIn()){
            //start login activity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivityForResult(intent,0);
        }
        else{
            Intent intent = new Intent(MainActivity.this, LoggedUserActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0) {
            //a log in activity was finished
            if (resultCode == 1) {
                //the log in was successful
                Log.e("pedro", sessionManager.getUserDetails().get("name"));
                Intent intent = new Intent(MainActivity.this, LoggedUserActivity.class);
                startActivityForResult(intent, 1);
            }
            else{
                //the user pressed the back button
                finish();
            }
        }else if(requestCode==1){
            //a logged user activity was finished
            if(resultCode==2){
                //the user logged out so start login activity again
                sessionManager.logoutUser();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent,0);
            }
            else{
                finish();
            }
        }
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

