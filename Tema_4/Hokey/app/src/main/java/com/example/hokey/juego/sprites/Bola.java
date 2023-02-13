package com.example.hokey.juego.sprites;

import android.graphics.Canvas;
import android.util.Log;

import com.example.hokey.juego.GameView;
import com.example.hokey.juego.Hokey;
import com.example.hokey.juego.OnColisionListener;
import com.example.hokey.juego.Utilidades;

public class Bola extends Sprite implements OnColisionListener {

    private Hokey game;
    public float centroX,centroY,radio;
    public boolean activa=true;
    public float rozamiento= (float) 0.999;

    public Bola(GameView game, float centroX, float centroY, float radio,int color) {
        super(game);
        this.game = (Hokey)game;
        this.centroX = centroX;
        this.centroY = centroY;
        this.radio = radio;
        this.color=color;
        this.velInicialX=0;
        this.velInicialY=0;
        velActualX=velInicialX;
        velActualY=velInicialY;
    }

    @Override
    public void setup() {
        this.velActualX=0;
        this.velActualY=0;
        this.velActualX=velInicialX;
        this.velActualY=velInicialY;

    }

    @Override
    public boolean colision(Sprite s){
        //Log.d(":::Colion","Entre");
        Ficha f=(Ficha)s;
        boolean col= Utilidades.colisionCirculos(centroX,centroY,radio,f.centroX,f.centroY,f.radio);
        if (!col) {
            //Log.d(":::Colion","If");
            activa=true;
        }
        return col;
    }

    @Override
    public void update() {
        //Se actualiza la posicion de la bola según la anterior
        velActualX*=rozamiento;
        //  if (velActualX==0);velActualX=0;
        centroX+=velActualX;
        velActualY*=rozamiento;
        //   if (velActualY==0)velActualY=0;
        centroY+=velActualY;
        //Log.d("billar",this.getVelActualX()+"----"+this.getVelActualX());
        //Comprobamos colisiones con los bordes y entre los actores
        onFireColisionSprite();
        onFireColisionBorder();
        //Se actualizan otras variables internas

    }


    @Override
    public void onFireColisionBorder(){
        if (this.centroX-radio<0)
            onColisionBorderEvent(OnColisionListener.LEFT);
        if (this.centroX+radio> game.getmScreenX())
            onColisionBorderEvent(OnColisionListener.RIGHT);
        if (this.centroY-radio < 0)
            onColisionBorderEvent(OnColisionListener.TOP);
        if (this.centroY+radio > game.getmScreenY())
            onColisionBorderEvent(OnColisionListener.BOTTOM);
    }


    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Ficha) {
            if(activa){
                Ficha f=(Ficha)s;
                if (centroX>f.centroX)
                    recolocaX((f.radio+radio-(centroX-f.centroX)));
                else
                    recolocaX(-1*(f.radio+radio-(f.centroX-centroX)));
                if (centroY>f.centroY)
                    recolocaY((f.radio+radio-(centroY-f.centroY)));
                else
                    recolocaY(-1*(f.radio+radio-(f.centroY-centroY)));
                float dy=(float)(f.centroY-centroY);
                float dx=(float)(f.centroX-centroX);
                float ang=(float)Math.atan2(dy,dx);
                double cosa=Math.cos(ang);
                double sina=Math.sin(ang);
                float vx2=(float)(cosa*f.velActualX+sina*f.velActualY);
                float vy1=(float)(cosa*f.velActualY-sina*f.velActualX);
                float vx1=(float)(cosa*velActualX+sina*velActualY);
                float vy2=(float)(cosa*velActualY-sina*velActualX);
                f.velActualX=(float)(cosa*vx1-sina*vy1);
                f.velActualY=(float)(cosa*vy1+sina*vx1);
                velActualX=(float)(cosa*vx2-sina*vy2);
                velActualY=(float)(cosa*vy2+sina*vx2);
            }
        }
    }


    @Override
    public void onColisionBorderEvent(int border) {

        switch (border){
            case OnColisionListener.TOP:
                velActualY=-velActualY;
                break;
            case OnColisionListener.BOTTOM:
                velActualY=-velActualY;
                break;
            case OnColisionListener.RIGHT:
                velActualX=-velActualX;
                break;
            case OnColisionListener.LEFT:
                velActualX=-velActualX;
                break;
            default:

                break;
        }
    }

    @Override
    public  void pinta(Canvas canvas){
        paint.setColor(this.color);
        canvas.drawCircle(centroX,centroY,radio,paint);
    }

    @Override
    public void recolocaX(float x) {
        Log.d(":::CentroX",x+"");
        centroX=(float)(centroX+x);
        Log.d(":::CentroX",centroX+"");
    }

    @Override
    public void recolocaY(float y) {
        Log.d(":::CentroY",y+"");
        centroY=(float)(centroY+y);
        Log.d(":::CentroY",centroY+"");
    }
}
