package com.example.hokey.juego;

import com.example.hokey.juego.sprites.Sprite;

public interface OnColisionListener {

    public final int TOP=0,BOTTOM=1,LEFT=2,RIGHT=3;
    public void onColisionEvent(Sprite s);
    public void onColisionBorderEvent(int border);
}
