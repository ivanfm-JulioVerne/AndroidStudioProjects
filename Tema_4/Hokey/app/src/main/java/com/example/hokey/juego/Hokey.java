package com.example.hokey.juego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.hokey.juego.sprites.Bola;
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
    public int ejecutaActionDown1(MotionEvent event) {
        float coorX=event.getX();
        float coorY=event.getY();

        int idInput=event.getPointerId(event.getActionIndex());

        if (Utilidades.distancia(coorX,coorY,ficha1.centroX,ficha1.centroY)<ficha1.radio){
            ficha1.tocado=true;
            ficha1.idInput=idInput;
        }else if (Utilidades.distancia(coorX,coorY,ficha2.centroX,ficha2.centroY)<ficha2.radio) {
            ficha2.tocado = true;
            ficha2.idInput=idInput;
        }
        return idInput;
    }

    @Override
    public void ejecutaActionUp1(MotionEvent event) {
        Log.d(":::1","1");
        if (ficha1.idInput==event.getPointerId(event.getActionIndex())){
            ficha1.tocado=false;
        }
        if (ficha2.idInput==event.getPointerId(event.getActionIndex())){
            ficha2.tocado=false;
        }
    }

    @Override
    public int ejecutaActionDown2(MotionEvent event, int actionIndex) {


        float coorX=event.getX(actionIndex);
        float coorY=event.getY(actionIndex);

        int idInput= event.getPointerId(actionIndex);

        if (Utilidades.distancia(coorX,coorY,ficha1.centroX,ficha1.centroY)<ficha1.radio){
            ficha1.tocado=true;
            ficha1.idInput=idInput;
        }else if (Utilidades.distancia(coorX,coorY,ficha2.centroX,ficha2.centroY)<ficha2.radio) {
            ficha2.tocado = true;
            ficha2.idInput=idInput;
        }

        return idInput;
    }

    @Override
    public void ejecutaActionUp2(MotionEvent event, int actionIndex) {
        Log.d(":::2",event.getPointerId(actionIndex)+"");
        if (ficha1.idInput==event.getPointerId(actionIndex)){
            Log.d(":::Entro","entor");
            ficha1.tocado=false;
        }
        if (ficha2.idInput==event.getPointerId(actionIndex)){
            ficha2.tocado=false;
        }
    }

    @Override
    public int ejecutaMove(MotionEvent event, int actionIndex) {
        /*int turno=actionIndex;
        if (actionIndex==0){
            Log.d("No","No");
            if (ficha1.idInput==0 && ficha1.tocado){
                float newX=event.getX(actionIndex);
                float newY=event.getY(actionIndex);
                if (Utilidades.distancia(newX,newY,ficha1.centroX,ficha1.centroY)<(getmScreenY()/5)) {
                    Log.d(":::0", "1");
                    ficha1.centroX = event.getX(actionIndex);
                    ficha1.centroY = event.getY(actionIndex);
                }
            }else if (ficha2.idInput==0 && ficha2.tocado){

                float newX=event.getX(actionIndex);
                float newY=event.getY(actionIndex);
                if (Utilidades.distancia(newX,newY,ficha2.centroX,ficha2.centroY)<(getmScreenY()/5)) {
                    Log.d(":::0", "2");
                    ficha2.centroX = event.getX(actionIndex);
                    ficha2.centroY = event.getY(actionIndex);
                }
            }
            turno=1;
        }else if (actionIndex==1){
            Log.d("Si","Si");
            if (ficha1.idInput==1 && ficha1.tocado){
                int idx=0;
                if (ficha2.tocado){
                    idx=1;
                }

                float newX=event.getX(idx);
                float newY=event.getY(idx);
                if (Utilidades.distancia(newX,newY,ficha1.centroX,ficha1.centroY)<(getmScreenY()/5)){
                    Log.d(":::1","1");
                    ficha1.centroX=event.getX(idx);
                    ficha1.centroY=event.getY(idx);
                }
            }else if (ficha2.idInput==1 && ficha2.tocado){
                int idx=0;
                if (ficha1.tocado){
                    idx=1;
                }
                float newX=event.getX(idx);
                float newY=event.getY(idx);
                if (Utilidades.distancia(newX,newY,ficha2.centroX,ficha2.centroY)<(getmScreenY()/5)) {
                    Log.d(":::1", "2");
                    ficha2.centroX = event.getX(idx);
                    ficha2.centroY = event.getY(idx);
                }
            }
            turno=0;
        }
        return turno;*/

        int ficha1Id=ficha1.idInput;
        int ficha2Id=ficha2.idInput;
        int idTurno;
        if(ficha1Id==event.getPointerId(actionIndex)){

            float newX=event.getX(event.getPointerId(actionIndex));
            float newY=event.getY(event.getPointerId(actionIndex));
            if (Utilidades.distancia(newX,newY,ficha1.centroX,ficha1.centroY)<(getmScreenY()/5)) {
                Log.d(":::Ficha1",ficha1Id+"");
                ficha1.centroX = event.getX(event.getPointerId(actionIndex));
                ficha1.centroY = event.getY(event.getPointerId(actionIndex));
            }
            if (ficha2.tocado){
                idTurno=ficha2.idInput;
            }else {
                idTurno = ficha1.idInput;
            }
        }else if (ficha2Id==event.getPointerId(actionIndex)){
            float newX=event.getX(event.getPointerId(actionIndex));
            float newY=event.getY(event.getPointerId(actionIndex));
            if (Utilidades.distancia(newX,newY,ficha2.centroX,ficha2.centroY)<(getmScreenY()/5)) {
                Log.d(":::Ficha2",ficha2Id+"");
                ficha2.centroX = event.getX(event.getPointerId(actionIndex));
                ficha2.centroY = event.getY(event.getPointerId(actionIndex));
            }
            if (ficha1.tocado){
                idTurno=ficha1.idInput;
            }else{
                idTurno=ficha2.idInput;
            }
        }else{
            idTurno=0;
        }

        return idTurno;
    }

    public void setupGame(){
        ficha1=new Ficha(this,getmScreenX()/2,500,100,Color.BLUE);

        ficha2=new Ficha(this,getmScreenX()/2,getmScreenY()-500,100,Color.YELLOW);

        Bola b=new Bola(this,getmScreenX()/2,getmScreenY()/2,100,Color.RED);
    }
}
