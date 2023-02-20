package com.example.hokey.juego;

import com.example.hokey.juego.sprites.Ficha;

public class Jugador {
    private String nombre;
    private int puntos;

    public Jugador(String nombre){
        this.nombre=nombre;
        this.puntos=0;
    }

    public void marca(){
        puntos++;
    }
}
