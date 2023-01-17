package com.example.rutapp.modelo;

import java.util.ArrayList;

public interface IPosicionDao {

    public ArrayList<PosicionConId> mostrarVarios(ArrayList<Posicion> ps);

    public ArrayList<Posicion> crear(ArrayList<Posicion> ps, Posicion p);

    public ArrayList<Posicion> actualizar(ArrayList<Posicion> ps, Posicion p, int id);

    public ArrayList<Posicion> borrar(ArrayList<Posicion> ps, int id);
}
