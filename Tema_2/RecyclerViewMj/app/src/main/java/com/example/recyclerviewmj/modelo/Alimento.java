package com.example.recyclerviewmj.modelo;

public class Alimento {
    private String nombre;
    private String  cantidad;
    private String  precio;
    private String distribuidor;
    private String tipo;


    public Alimento() {
    }

    public Alimento(String nombre, String  precio, String distribuidor, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.distribuidor = distribuidor;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String  getCantidad() {
        return cantidad;
    }

    public void setCantidad(String  cantidad) {
        this.cantidad = cantidad;
    }

    public String  getPrecio() {
        return precio;
    }

    public void setPrecio(String  precio) {
        this.precio = precio;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
