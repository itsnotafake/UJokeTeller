package com.udacity.gradle.androidjoke;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.gradle.tasks.EndpointsAsyncTask;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "jk";
    private ProgressBar mPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        mPB = (ProgressBar) findViewById(R.id.joke_progress);
        mPB.setVisibility(View.GONE);
        mPB.setVisibility(View.VISIBLE);

        new EndpointsAsyncTask().execute(this);
    }
}
