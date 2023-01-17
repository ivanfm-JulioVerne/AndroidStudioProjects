package com.example.rutapp.modelo;

public class PosicionConId {
    private int id;
    private int x;
    private int y;
    private String descripcion;

    public PosicionConId(int id, int x, int y, String descripcion) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
