package com.itdeveapps.omar.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itdeveapps.omar.builditbigger.jokelib.JokeActivity;

public class MainActivity extends AppCompatActivity implements JokerApiAsyncTask.OnJokerAsyncTaskCompleted{
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Button tellAJokeButton = findViewById(R.id.tellAJokeButton);
        tellAJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new JokerApiAsyncTask(MainActivity.this).execute();
            }
        });
    }

    public void onTaskError(){
        if(progressBar==null)
            return;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, "Error getting your joke!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTaskCompleted(String result) {
        if(progressBar == null)
            return;
        progressBar.setVisibility(View.GONE);

        if (TextUtils.isEmpty(result)) {
             onTaskError();
            return;
        }

        Intent intent = new Intent(MainActivity.this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, result);
        startActivity(intent);
    }
}
