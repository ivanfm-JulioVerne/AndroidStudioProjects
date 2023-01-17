package com.example.rutapp.modelo;

public class Posicion {
    private int x;
    private int y;
    private String descripcion;

    public Posicion() {
    }

    public Posicion(int x, int y, String descripcion) {
        this.x = x;
        this.y = y;
        this.descripcion = descripcion;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
