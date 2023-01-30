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
    public void ejecutaActionDown1(MotionEvent event) {

        float coorX=event.getX();
        float coorY=event.getY();

        if (Utilidades.distancia(coorX,coorY,ficha1.centroX,ficha1.centroY)<ficha1.radio){
            ficha1.tocado=true;
            ficha1.idInput=0;
        }else if (Utilidades.distancia(coorX,coorY,ficha2.centroX,ficha2.centroY)<ficha2.radio) {
            ficha2.tocado = true;
            ficha2.idInput=0;
        }
    }

    @Override
    public void ejecutaActionUp1(MotionEvent event) {
        if (ficha1.tocado){
            ficha1.tocado=false;
            Log.d(":::Si","si");
        }
        if (ficha2.tocado){
            ficha2.tocado=false;
        }
    }

    @Override
    public void ejecutaActionDown2(MotionEvent event, int actionIndex) {

        float coorX=event.getX(actionIndex);
        float coorY=event.getY(actionIndex);

        if (Utilidades.distancia(coorX,coorY,ficha1.centroX,ficha1.centroY)<ficha1.radio){
            ficha1.tocado=true;
            ficha1.idInput=1;
        }else if (Utilidades.distancia(coorX,coorY,ficha2.centroX,ficha2.centroY)<ficha2.radio) {
            ficha2.tocado = true;
            ficha2.idInput=1;
        }
    }

    @Override
    public void ejecutaActionUp2(MotionEvent event, int actionIndex) {
        if (ficha1.tocado){
            ficha1.tocado=false;
        }
        if (ficha2.tocado){
            ficha2.tocado=false;
        }
    }

    @Override
    public void ejecutaMove(MotionEvent event, int actionIndex) {
        if (actionIndex==0){
            if (ficha1.idInput==0 && ficha1.tocado){
                Log.d(":::0","1");
                ficha1.centroX=event.getX(actionIndex);
                ficha1.centroY=event.getY(actionIndex);
            }else if (ficha2.idInput==0 && ficha2.tocado){
                Log.d(":::0","2");
                ficha2.centroX=event.getX(actionIndex);
                ficha2.centroY=event.getY(actionIndex);
            }
        }else if (actionIndex==1){
            if (ficha1.idInput==1 && ficha1.tocado){
                Log.d(":::1","1");
                ficha1.centroX=event.getX(actionIndex);
                ficha1.centroY=event.getY(actionIndex);
            }else if (ficha2.idInput==1 && ficha2.tocado){
                Log.d(":::1","2");
                ficha2.centroX=event.getX(actionIndex);
                ficha2.centroY=event.getY(actionIndex);
            }
        }
    }

    public void setupGame(){
        ficha1=new Ficha(this,getmScreenX()/2,500,100,Color.BLUE);

        ficha2=new Ficha(this,getmScreenX()/2,getmScreenY()-500,100,Color.YELLOW);
    }
}
