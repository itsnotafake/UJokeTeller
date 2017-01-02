package com.udacity.gradle.androidjoke;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import com.udacity.gradle.tasks.EndpointsAsyncTask;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "jk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        new EndpointsAsyncTask().execute(this);
    }
}
