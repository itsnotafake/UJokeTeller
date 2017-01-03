package com.udacity.gradle.tasks;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.androidjoke.JokeActivity;
import com.udacity.gradle.androidjoke.R;
import com.udacity.gradle.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Devin on 12/27/2016.
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    public String testString;

    public EndpointsAsyncTask(){

    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        context = params[0];

        try{
            testString = myApiService.sayJoke().execute().getData();
            return testString;
        }catch(IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result){
        /*TextView tv = (TextView) activity.findViewById(R.id.joke_joke);
        if(tv != null) {
            tv.setText(result);
        }*/
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE, result);
        context.startActivity(intent);
    }
}
