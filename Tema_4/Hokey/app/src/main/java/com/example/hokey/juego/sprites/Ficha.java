package com.example.hokey.juego.sprites;

import android.graphics.Canvas;
import android.util.Log;

import com.example.hokey.juego.GameView;
import com.example.hokey.juego.Hokey;
import com.example.hokey.juego.OnColisionListener;
import com.example.hokey.juego.Utilidades;

public class Ficha extends Sprite implements OnColisionListener {

    private Hokey game;
    public float centroX,centroY,radio;
    public boolean activa=true;
    public boolean tocado;
    public float centroXOrigen,centroYOrigen;

    public int idInput;

    public Ficha(GameView game, float centroX, float centroY, float radio,int color) {
        super(game);
        this.game = (Hokey)game;
        this.centroX = centroX;
        this.centroY = centroY;
        this.radio = radio;
        this.color=color;
        this.idInput= -1;
        this.velActualX=0;
        this.velActualY=0;
    }

    @Override
    public void onColisionEvent(Sprite s) {
        Log.d(":::OnColisionEvent Ficha","Entra");
    }

    @Override
    public void onColisionBorderEvent(int border) {

    }

    @Override
    public void onFireColisionBorder() {

    }

    @Override
    public boolean colision(Sprite s) {
        //Log.d(":::Colision","Entra");
        Bola b=(Bola)s;
        boolean col= Utilidades.colisionCirculos(centroX,centroY,radio,b.centroX,b.centroY,b.radio);
        if (!col) {
            //Log.d(":::Colion","If");
            activa=true;
        }
        return col;
    }

    @Override
    public void pinta(Canvas canvas) {
        paint.setColor(this.color);
        canvas.drawCircle(centroX,centroY,radio,paint);
    }

    @Override
    public void recolocaX(float x) {

    }

    @Override
    public void recolocaY(float y) {

    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {
        if((centroX-centroXOrigen)!=0)
            velActualX=(centroX-centroXOrigen);
        if((centroY-centroYOrigen)!=0)
            velActualY=(centroY-centroYOrigen);

        centroXOrigen=centroX;
        centroYOrigen=centroY;
    }
}
