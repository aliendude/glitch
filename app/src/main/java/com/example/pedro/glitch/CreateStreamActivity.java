package com.example.pedro.glitch;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.pedro.glitch.Data.StreamAsyncTask;
import com.example.pedro.glitch.Fragments.CreateStreamFragment;
import com.example.pedro.myapplication.backend1.streams.model.Stream;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateStreamActivity extends ActionBarActivity implements CreateStreamFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stream);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.create_stream_content, new CreateStreamFragment());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_stream, menu);
        return true;
    }
    public void onButtonCreateCoveragePressed(View v) {
        Intent intent = new Intent(CreateStreamActivity.this, CreateCoverageActivity.class);
        startActivity(intent);
    }
    public void onButtonStartStreamPressed(View v){

        EditText descEt= (EditText)findViewById(R.id.streamDescriptionEditText  );
        String desc=descEt.getText()+"";
        EditText nameEt= (EditText)findViewById(R.id.streamNameEditText );
        String name=nameEt.getText()+"";
        EditText endTimeEt= (EditText)findViewById(R.id.streamEndTimeEditText);
        String endTime=endTimeEt.getText()+"";

        String str="createStream";
        Stream m = new Stream();

        m.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        m.setOnline(false);
        m.setName(name);
        m.setDescription(desc);
        m.setEndTime(endTime);

        StreamAsyncTask callbackend1 = new StreamAsyncTask(this);
        callbackend1.execute(new Pair<String,Stream>(str,m));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
