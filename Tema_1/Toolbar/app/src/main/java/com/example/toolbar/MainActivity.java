package com.example.toolbar;

import static com.example.toolbar.R.id.toolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MENU");
        toolbar.setSubtitle("SUBTITULO");
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }



}