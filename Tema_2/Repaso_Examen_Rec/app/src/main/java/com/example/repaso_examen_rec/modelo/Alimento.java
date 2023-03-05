package com.example.repaso_examen_rec.modelo;

public class Alimento {
    private String nombre;
    private float numKcal;

    public Alimento(String nombre, float numKcal) {
        this.nombre = nombre;
        this.numKcal = numKcal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getNumKcal() {
        return numKcal;
    }

    public void setNumKcal(float numKcal) {
        this.numKcal = numKcal;
    }
}
