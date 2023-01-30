package com.example.hokey.juego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.hokey.juego.sprites.Ficha;

public class Hokey extends GameView implements OnTouchEventListener{

    Ficha ficha1,ficha2;

    public Hokey(Context context, int x, int y) {
        super(context, x, y);
        addOnTouchEventListener(this);
        setupGame();
    }

    @Override
    public void dibuja(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 217, 255, 249));
        paint.setColor(Color.RED);
        canvas.drawLine(0,getmScreenY()/2,getmScreenX(),getmScreenY()/2,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getmScreenX()/2,getmScreenY()/2,200,paint);
        canvas.drawRect(new Rect(20,20,getmScreenX()-20,getmScreenY()-20),paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawRect(getmScreenX()/2-200,0,getmScreenX()/2+200,20,paint);
        canvas.drawRect(getmScreenX()/2-200,getmScreenY()-20,getmScreenX()/2+200,getmScreenY(),paint);

        ficha1.pinta(canvas);
        ficha2.pinta(canvas);
    }

    @Override
    protected void actualiza() {

    }

    @Override
    public void ejecutaActionDown(MotionEvent event) {
        float coorX=event.getX();
        float coorY=event.getY();

        if (Utilidades.distancia(coorX,coorY,ficha1.centroX,ficha1.centroY)<ficha1.radio){
            ficha1.tocado=true;
        }else  if (Utilidades.distancia(coorX,coorY,ficha2.centroX,ficha2.centroY)<ficha2.radio) {
            ficha2.tocado = true;
        }
    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        if (ficha1.tocado){
            ficha1.tocado=false;
        }else if (ficha2.tocado){
            ficha2.tocado=false;
        }
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        if(ficha1.tocado){
            ficha1.centroX=event.getX();
            ficha1.centroY=event.getY();
        }else if(ficha2.tocado){
            ficha2.centroX=event.getX();
            ficha2.centroY=event.getY();
        }
    }

    public void setupGame(){
        ficha1=new Ficha(this,getmScreenX()/2,500,100,Color.BLUE);

        ficha2=new Ficha(this,getmScreenX()/2,getmScreenY()-500,100,Color.YELLOW);
    }
}
