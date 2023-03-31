package com.example.rutasivanfernandezmendez.modelo;

public class Lugar {
    private String nombre;
    private float kms;

    public Lugar(String nombre, float kms) {
        this.nombre = nombre;
        this.kms = kms;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getKms() {
        return kms;
    }

    public void setKms(float kms) {
        this.kms = kms;
    }
}
