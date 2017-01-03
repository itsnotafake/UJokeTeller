package com.udacity.gradle.androidjoke;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "jk";
    private ProgressBar mPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        TextView jokeView = (TextView) findViewById(R.id.joke_joke);
        jokeView.setText(getIntent().getStringExtra(JOKE));
    }
}
