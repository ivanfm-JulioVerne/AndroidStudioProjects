package com.example.videojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.videojuego.sprites.Bola;

import com.example.videojuego.sprites.Sprite;

import java.util.LinkedList;

public class Billar extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;

    //Actores del juego
    Bola bola1,bola2, bola3, bola4, bola5, bola6;;
    //

    float lineX1,lineY1,lineX2,lineY2;
    boolean estaDentro=false;
    boolean apunta=false;



    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;


    public Billar(Context context, int x, int y) {
        super(context,x,y);
        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();
    }

    public void setupGame() {

        bola3 = new Bola(this, 300, 100, 50,Color.BLUE);
        actores.add(bola3);  bola3.setup();
        bola1 = new Bola(this,300, 300, 50,Color.WHITE);
        actores.add(bola1);  bola1.setup();
        bola2 = new Bola(this, 300, 500, 50,Color.RED);
        actores.add(bola2);  bola2.setup();
        bola4 = new Bola(this, 300, 700, 50,Color.YELLOW);
        actores.add(bola4);  bola4.setup();
        bola5 = new Bola(this, 300, 900, 50,Color.BLACK);
        bola6= new Bola(this, 300,1000, 50,Color.GREEN);

        actores.add(bola5);  bola5.setup();
        actores.add(bola6);  bola6.setup();


    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    public void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if(actor.isVisible())
               actor.update();
        }
    }

    //dibuja la pantalla
    @Override
    public void dibuja(Canvas canvas) {
        //se pinta desde la capa más lejana hasta la más cercana
        canvas.drawColor(Color.argb(255, 20, 128, 188));
        synchronized(actores) {
            for (Sprite actor : actores) {
                    actor.pinta(canvas);
            }
        }
        //dibujamos puntuacion y vidas
        paint.setTextSize(30);
        canvas.drawText("Factor_mov: " + this.factor_mov + "  Vidas: " + actores.size(), 10, 50, paint);
        paint.setTextSize(10);
        if(estaDentro){
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(5);
            canvas.drawLine(bola1.centroX,bola1.centroY,lineX2,lineY2,paint);
          if(apunta){
              paint.setColor(Color.RED);
              canvas.drawLine(bola1.centroX,bola1.centroY,(bola1.centroX-lineX2)*1000,(bola1.centroY-lineY2)*1000,paint);

          }

        }

    }

  //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {
        lineX1=event.getX();
        lineY1=event.getY();
        if (Utilidades.distancia(lineX1,lineY1,bola1.centroX,bola1.centroY)<bola1.radio){
            estaDentro=true;
            lineX1=bola1.centroX;
            lineY1=bola1.centroY;
            lineX2=bola1.centroX;
            lineY2=bola1.centroY;

        }
        Log.d("billar","X: "+lineX1+" Y: "+lineY1);

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        if(estaDentro){
            lineX2=event.getX();
            lineY2=event.getY();
            bola1.setVelActualX((lineX1-lineX2)/10);
            bola1.setVelActualY((lineY1-lineY2)/10);
            estaDentro=false;
            apunta=false;
        }
        Log.d("billar",bola1.getVelActualX()+"----"+bola1.getVelActualY());
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        //Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        apunta=true;
        lineX2=event.getX();
        lineY2=event.getY();

    }


}
