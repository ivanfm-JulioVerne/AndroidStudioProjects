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
import com.example.hokey.juego.sprites.Sprite;

public class Hokey extends GameView implements OnTouchEventListener{

    Ficha ficha1,ficha2;

    Bola disco;

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
        disco.pinta(canvas);
    }

    @Override
    protected void actualiza() {
        for (Sprite actor : actores) {
            if(actor.isVisible())
                actor.update();
        }
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
        if (ficha1.idInput==event.getPointerId(actionIndex)){
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
        int idEvento;
        try{
            idEvento=event.getPointerId(actionIndex);
        }catch (Exception e){
            idEvento=event.getPointerId(event.getActionIndex());
        }

        if(ficha1Id==idEvento && ficha1.tocado){
            float newX;
            float newY;
            try{
                newX=event.getX(idEvento);
                newY=event.getY(idEvento);
            }catch(Exception e){
                newX=event.getX(idEvento-1);
                newY=event.getY(idEvento-1);
            }

            if (Utilidades.distancia(newX,newY,ficha1.centroX,ficha1.centroY)<(getmScreenY()/5)) {
                try{
                    ficha1.centroX = event.getX(idEvento);
                    ficha1.centroY = event.getY(idEvento);
                }catch(Exception e) {
                    ficha1.centroX = event.getX(idEvento - 1);
                    ficha1.centroY = event.getY(idEvento - 1);
                }
            }
            if (ficha2.tocado){
                idTurno=ficha2.idInput;
            }else {
                idTurno = ficha1.idInput;
            }
        }else if (ficha2Id==idEvento && ficha2.tocado){
            float newX;
            float newY;
            try{
                newX=event.getX(idEvento);
                newY=event.getY(idEvento);
            }catch(Exception e){
                newX=event.getX(idEvento-1);
                newY=event.getY(idEvento-1);
            }
            if (Utilidades.distancia(newX,newY,ficha2.centroX,ficha2.centroY)<(getmScreenY()/5)) {
                try{
                    ficha2.centroX = event.getX(idEvento);
                    ficha2.centroY = event.getY(idEvento);
                }catch(Exception e) {
                    ficha2.centroX = event.getX(idEvento - 1);
                    ficha2.centroY = event.getY(idEvento - 1);
                }
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

        disco=new Bola(this,getmScreenX()/2,getmScreenY()/2,100,Color.RED);

        actores.add(ficha1); disco.setup();
        actores.add(ficha2);
        actores.add(disco);
    }
}
