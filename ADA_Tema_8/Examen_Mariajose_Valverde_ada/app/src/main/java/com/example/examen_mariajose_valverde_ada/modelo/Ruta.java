package com.example.examen_mariajose_valverde_ada.modelo;

import com.example.examen_mariajose_valverde_ada.daos.DaoRuta;

public class Ruta implements DaoRuta {
    int longInicial,latInicial,rumbo,distancia;
    public Ruta(int longInicial,int latInicial,int rumbo,int distancia){
        this.longInicial=longInicial;
        this.latInicial=latInicial;
        this.rumbo=rumbo;
        this.distancia=distancia;
    }
    @Override
    public String toString(){

        return "Longitud Inicial: "+longInicial+" Latitud Inicial: "+ latInicial+" Rumbo: " +rumbo +" Distancia: "+distancia;
    }

    @Override
    public int getLatInicial() {
        return latInicial;
    }

    @Override
    public int getLongInicial() {
        return longInicial;
    }

    @Override
    public int getRumbo() {
        return rumbo;
    }

    @Override
    public int getDistancia() {
        return distancia;
    }
}
