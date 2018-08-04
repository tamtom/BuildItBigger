package com.itdeveapps.omar.builditbigger.jokelib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "jokeExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTv = findViewById(R.id.jokeTv);
        jokeTv.setText(getIntent().getStringExtra(JOKE_EXTRA));
    }
}
