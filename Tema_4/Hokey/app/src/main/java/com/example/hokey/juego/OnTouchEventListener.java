package com.example.hokey.juego;

import android.view.MotionEvent;

public interface OnTouchEventListener {
    public abstract int ejecutaActionDown1(MotionEvent event);

    public abstract void ejecutaActionUp1(MotionEvent event);

    public abstract int ejecutaMove(MotionEvent event, int actionIndex);

    public abstract int ejecutaActionDown2(MotionEvent event, int actionIndex);

    public abstract void ejecutaActionUp2(MotionEvent event, int actionIndex);
}
