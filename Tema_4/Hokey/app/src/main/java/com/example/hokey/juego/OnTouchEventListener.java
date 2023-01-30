package com.example.hokey.juego;

import android.view.MotionEvent;

public interface OnTouchEventListener {
    public abstract void ejecutaActionDown1(MotionEvent event);
    public abstract void ejecutaActionUp1(MotionEvent event);
    public abstract void ejecutaMove(MotionEvent event, int actionIndex);
    public abstract void ejecutaActionDown2(MotionEvent event, int actionIndex);
    public abstract void ejecutaActionUp2(MotionEvent event, int actionIndex);
}
