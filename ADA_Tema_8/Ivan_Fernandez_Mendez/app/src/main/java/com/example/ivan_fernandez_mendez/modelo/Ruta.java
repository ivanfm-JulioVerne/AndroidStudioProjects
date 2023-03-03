package com.example.ivan_fernandez_mendez.modelo;

public class Ruta {
    private double longInicial;
    private double latInicial;
    private double rumbo;
    private double distancia;

    public Ruta(double longInicial, double latInicial, double rumbo, double distancia) {
        this.longInicial = longInicial;
        this.latInicial = latInicial;
        this.rumbo = rumbo;
        this.distancia = distancia;
    }

    public double getLongInicial() {
        return longInicial;
    }

    public void setLongInicial(double longInicial) {
        this.longInicial = longInicial;
    }

    public double getLatInicial() {
        return latInicial;
    }

    public void setLatInicial(double latInicial) {
        this.latInicial = latInicial;
    }

    public double getRumbo() {
        return rumbo;
    }

    public void setRumbo(double rumbo) {
        this.rumbo = rumbo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distamcia) {
        this.distancia = distamcia;
    }
}
