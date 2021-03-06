package com.example.pedro.glitch.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.pedro.glitch.Constants;
import com.example.pedro.myapplication.backend1.coverages.Coverages;
import com.example.pedro.myapplication.backend1.coverages.model.Coverage;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pedro on 10/25/15.
 */
public class CreateCoverageAsyncTask extends AsyncTask<Pair<String, Coverage>, Void, String> {
    private Coverages coverageService = null;
    private GoogleCloudMessaging gcm;
    private Context context;

    public CreateCoverageAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<String, Coverage>... params) {
        if (coverageService == null) {
            Coverages.Builder builder = new Coverages.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // Need setRootUrl and setGoogleClientRequestInitializer only for local testing,
                    // otherwise they can be skipped
                    //.setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setRootUrl(Constants.APPENGINE_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end of optional local run code
            coverageService = builder.build();
        }

        String msg = "";
        try {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(context);
            }


            String data = params[0].first;
            Log.e("pedro",data);
            Coverage coverage = params[0].second;
            coverageService.addCoverage(data,coverage).execute();
            msg = "Coverage created";


        } catch (IOException ex) {
            ex.printStackTrace();
            msg = "Error: " + ex.getMessage();
        }
        return msg;
    }

    @Override
    protected void onPostExecute(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        Logger.getLogger("Marker created").log(Level.INFO, msg);
    }

}