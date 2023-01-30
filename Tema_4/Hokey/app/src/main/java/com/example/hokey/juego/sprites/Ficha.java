package com.example.hokey.juego.sprites;

import android.graphics.Canvas;

import com.example.hokey.juego.GameView;
import com.example.hokey.juego.Hokey;
import com.example.hokey.juego.OnColisionListener;

public class Ficha extends Sprite implements OnColisionListener {

    private Hokey game;
    public float centroX,centroY,radio;
    public boolean activa=true;
    public boolean tocado;

    public int idInput;

    public Ficha(GameView game, float centroX, float centroY, float radio,int color) {
        super(game);
        this.game = (Hokey)game;
        this.centroX = centroX;
        this.centroY = centroY;
        this.radio = radio;
        this.color=color;
        this.idInput= -1;
    }

    @Override
    public void onColisionEvent(Sprite s) {

    }

    @Override
    public void onColisionBorderEvent(int border) {

    }

    @Override
    public void onFireColisionBorder() {

    }

    @Override
    public boolean colision(Sprite s) {
        return false;
    }

    @Override
    public void pinta(Canvas canvas) {
        paint.setColor(this.color);
        canvas.drawCircle(centroX,centroY,radio,paint);
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {

    }
}
