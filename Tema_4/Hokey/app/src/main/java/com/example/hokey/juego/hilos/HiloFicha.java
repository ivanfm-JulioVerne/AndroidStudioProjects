package com.example.hokey.juego.hilos;

import android.content.Context;
import android.view.SurfaceView;

import com.example.hokey.juego.sprites.Ficha;

public class HiloFicha extends SurfaceView implements Runnable{

    Ficha ficha;

    public HiloFicha(Context context, Ficha ficha){
        super(context);
        this.ficha=ficha;
    }

    @Override
    public void run() {

    }
}
