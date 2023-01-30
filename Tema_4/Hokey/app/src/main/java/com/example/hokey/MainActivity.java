package com.example.hokey;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.example.hokey.juego.GameView;
import com.example.hokey.juego.Hokey;

public class MainActivity extends AppCompatActivity {

    GameView juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);

        juego=new Hokey(this,size.x,size.y);

        setContentView(juego);
    }

    @Override
    protected void onResume() {
        super.onResume();
        juego.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        juego.pause();
    }
}