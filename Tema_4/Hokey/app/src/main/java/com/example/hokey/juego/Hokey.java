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

public class Hokey extends GameView implements OnTouchEventListener {

    Ficha ficha1, ficha2;

    Bola disco;

    int puntoJ1 = 0;
    int puntoJ2 = 0;
    int limitePuntos = 20;
    boolean ganador1 = false;
    boolean ganador2 = false;

    public Hokey(Context context, int x, int y) {
        super(context, x, y);
        addOnTouchEventListener(this);
        setupGame();
    }

    @Override
    public void dibuja(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 217, 255, 249));
        paint.setColor(Color.RED);
        canvas.drawLine(0, getmScreenY() / 2, getmScreenX(), getmScreenY() / 2, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getmScreenX() / 2, getmScreenY() / 2, 200, paint);
        canvas.drawRect(new Rect(20, 20, getmScreenX() - 20, getmScreenY() - 20), paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawRect(getmScreenX() / 2 - 200, 0, getmScreenX() / 2 + 200, 20, paint);
        canvas.drawRect(getmScreenX() / 2 - 200, getmScreenY() - 20, getmScreenX() / 2 + 200, getmScreenY(), paint);

        ficha1.pinta(canvas);
        ficha2.pinta(canvas);
        disco.pinta(canvas);

        paint.setColor(Color.GREEN);
        paint.setTextSize(100);
        canvas.rotate(-90);
        if (puntoJ1 < 10 && puntoJ2 < 10) {
            canvas.drawText("0" + puntoJ1 + " : 0" + puntoJ2, -getmScreenY() / 2 - 150, 100, paint);
        } else if (puntoJ1 < 10 && !(puntoJ2 < 10)) {
            canvas.drawText("0" + puntoJ1 + " : " + puntoJ2, -getmScreenY() / 2 - 150, 100, paint);
        } else if (!(puntoJ1 < 10) && puntoJ2 < 10) {
            canvas.drawText(puntoJ1 + " : 0" + puntoJ2, -getmScreenY() / 2 - 150, 100, paint);
        } else if (!(puntoJ1 < 10) && !(puntoJ2 < 10)) {
            canvas.drawText(puntoJ1 + " : " + puntoJ2, -getmScreenY() / 2 - 150, 100, paint);
        }
        canvas.rotate(180);
        if (puntoJ1 < 10 && puntoJ2 < 10) {
            canvas.drawText("0" + puntoJ2 + " : 0" + puntoJ1, getmScreenY() / 2 - 150, -getmScreenX() / 2 - 440, paint);
        } else if (puntoJ1 < 10 && !(puntoJ2 < 10)) {
            canvas.drawText("0" + puntoJ2 + " : " + puntoJ1, getmScreenY() / 2 - 150, -getmScreenX() / 2 - 440, paint);
        } else if (!(puntoJ1 < 10) && puntoJ2 < 10) {
            canvas.drawText(puntoJ2 + " : 0" + puntoJ1, getmScreenY() / 2 - 150, -getmScreenX() / 2 - 440, paint);
        } else if (!(puntoJ1 < 10) && !(puntoJ2 < 10)) {
            canvas.drawText(puntoJ2 + " : " + puntoJ1, getmScreenY() / 2 - 150, -getmScreenX() / 2 - 440, paint);
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.rotate(-90);
        if(ganador1 || ganador2){
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(200,getmScreenY()/3,getmScreenX()-200,getmScreenY()/3+getmScreenY()/3,paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawRect(200,getmScreenY()/3,getmScreenX()-200,getmScreenY()/3+getmScreenY()/3,paint);
            paint.setColor(Color.BLACK);
            canvas.drawText("GANADOR:", 290, getmScreenY() / 2 -280, paint);
            if (ganador1) {
                canvas.drawText("AMARILLAS", 265, getmScreenY() / 2 -160, paint);
            } else if (ganador2) {
                canvas.drawText("AZULES", 360, getmScreenY() / 2 -160, paint);
            }
            paint.setTextSize(50);
            canvas.drawText("Pulsa la pantalla", 350, getmScreenY() / 2+200, paint);
            canvas.drawText("volver a jugar", 390, getmScreenY() / 2+310, paint);
        }
    }

    @Override
    protected void actualiza() {
        for (Sprite actor : actores) {
            if (actor.isVisible())
                actor.update();
        }
    }

    @Override
    public int ejecutaActionDown1(MotionEvent event) {
        int idInput = event.getPointerId(event.getActionIndex());
        if (!ganador1 && !ganador2) {
            float coorX = event.getX();
            float coorY = event.getY();

            idInput = event.getPointerId(event.getActionIndex());

            if (Utilidades.distancia(coorX, coorY, ficha1.centroX, ficha1.centroY) < ficha1.radio) {
                ficha1.tocado = true;
                ficha1.idInput = idInput;
            } else if (Utilidades.distancia(coorX, coorY, ficha2.centroX, ficha2.centroY) < ficha2.radio) {
                ficha2.tocado = true;
                ficha2.idInput = idInput;
            }
        }else{
            ganador2=false;
            ganador1=false;
            puntoJ1 = 0;
            puntoJ2 = 0;
        }
        return idInput;

    }

    @Override
    public void ejecutaActionUp1(MotionEvent event) {
        if (!ganador1 && !ganador2) {
            if (ficha1.idInput == event.getPointerId(event.getActionIndex())) {
                ficha1.tocado = false;
            }
            if (ficha2.idInput == event.getPointerId(event.getActionIndex())) {
                ficha2.tocado = false;
            }
        }
    }

    @Override
    public int ejecutaActionDown2(MotionEvent event, int actionIndex) {
        int idInput = event.getPointerId(event.getActionIndex());
        if (!ganador1 && !ganador2) {
            float coorX = event.getX(actionIndex);
            float coorY = event.getY(actionIndex);

            idInput = event.getPointerId(actionIndex);

            if (Utilidades.distancia(coorX, coorY, ficha1.centroX, ficha1.centroY) < ficha1.radio) {
                ficha1.tocado = true;
                ficha1.idInput = idInput;
            } else if (Utilidades.distancia(coorX, coorY, ficha2.centroX, ficha2.centroY) < ficha2.radio) {
                ficha2.tocado = true;
                ficha2.idInput = idInput;
            }
        }
        return idInput;
    }

    @Override
    public void ejecutaActionUp2(MotionEvent event, int actionIndex) {
        if (!ganador1 && !ganador2) {
            if (ficha1.idInput == event.getPointerId(actionIndex)) {
                ficha1.tocado = false;
            }
            if (ficha2.idInput == event.getPointerId(actionIndex)) {
                ficha2.tocado = false;
            }
        }
    }

    @Override
    public int ejecutaMove(MotionEvent event, int actionIndex) {
        int idTurno = 0;
        if (!ganador1 && !ganador2) {
            int ficha1Id = ficha1.idInput;
            int ficha2Id = ficha2.idInput;

            int idEvento;
            try {
                idEvento = event.getPointerId(actionIndex);
            } catch (Exception e) {
                idEvento = event.getPointerId(event.getActionIndex());
            }

            if (ficha1Id == idEvento && ficha1.tocado) {
                float newX;
                float newY;
                try {
                    newX = event.getX(idEvento);
                    newY = event.getY(idEvento);
                } catch (Exception e) {
                    newX = event.getX(idEvento - 1);
                    newY = event.getY(idEvento - 1);
                }

                if (Utilidades.distancia(newX, newY, ficha1.centroX, ficha1.centroY) < (getmScreenY() / 5)) {
                    try {
                        ficha1.centroX = event.getX(idEvento);
                        ficha1.centroY = event.getY(idEvento);
                    } catch (Exception e) {
                        ficha1.centroX = event.getX(idEvento - 1);
                        ficha1.centroY = event.getY(idEvento - 1);
                    }
                }
                if (ficha2.tocado) {
                    idTurno = ficha2.idInput;
                } else {
                    idTurno = ficha1.idInput;
                }
            } else if (ficha2Id == idEvento && ficha2.tocado) {
                float newX;
                float newY;
                try {
                    newX = event.getX(idEvento);
                    newY = event.getY(idEvento);
                } catch (Exception e) {
                    newX = event.getX(idEvento - 1);
                    newY = event.getY(idEvento - 1);
                }
                if (Utilidades.distancia(newX, newY, ficha2.centroX, ficha2.centroY) < (getmScreenY() / 5)) {
                    try {
                        ficha2.centroX = event.getX(idEvento);
                        ficha2.centroY = event.getY(idEvento);
                    } catch (Exception e) {
                        ficha2.centroX = event.getX(idEvento - 1);
                        ficha2.centroY = event.getY(idEvento - 1);
                    }
                }
                if (ficha1.tocado) {
                    idTurno = ficha1.idInput;
                } else {
                    idTurno = ficha2.idInput;
                }
            } else {
                idTurno = 0;
            }
        }
        return idTurno;

    }

    public void setupGame() {
        ficha1 = new Ficha(this, getmScreenX() / 2, 500, 100, Color.BLUE);

        ficha2 = new Ficha(this, getmScreenX() / 2, getmScreenY() - 500, 100, Color.YELLOW);

        disco = new Bola(this, getmScreenX() / 2, getmScreenY() / 2, 75, Color.RED);

        actores.add(ficha1);
        disco.setup();
        actores.add(ficha2);
        actores.add(disco);
    }

    public void golJ1() {
        puntoJ1++;
        Log.d(":::Gol1", puntoJ1 + "");
        if (puntoJ1 == limitePuntos) {
            Log.d(":::Ganador", "Jugador 1");
            ganador1 = true;
        }
        ficha1.centroX = getmScreenX() / 2;
        ficha1.centroY = 500;
        ficha1.setVelActualX(10);
        ficha1.setVelActualY(10);

        ficha2.centroX = getmScreenX() / 2;
        ficha2.centroY = getmScreenY() - 500;

        disco.centroX = getmScreenX() / 2;
        disco.centroY = getmScreenY() / 2;
    }

    public void golJ2() {
        puntoJ2++;
        Log.d(":::Gol2", puntoJ2 + "");
        if (puntoJ2 == limitePuntos) {
            Log.d(":::Ganador", "Jugador 2");
            ganador2 = true;
        }
        ficha1.centroX = getmScreenX() / 2;
        ficha1.centroY = 500;
        ficha1.setVelActualX(10);
        ficha1.setVelActualY(10);

        ficha2.centroX = getmScreenX() / 2;
        ficha2.centroY = getmScreenY() - 500;

        disco.centroX = getmScreenX() / 2;
        disco.centroY = getmScreenY() / 2;
    }
}
