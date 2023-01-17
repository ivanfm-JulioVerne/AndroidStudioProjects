package com.example.rutapp.modelo;

import java.util.ArrayList;

public class PosicionDao implements IPosicionDao{

    @Override
    public ArrayList<PosicionConId> mostrarVarios(ArrayList<Posicion> ps) {
        ArrayList<PosicionConId> pcis=new ArrayList<PosicionConId>();
        for (int i=0;i<ps.size();i++){
            pcis.add(new PosicionConId(i,ps.get(i).getX(),
                    ps.get(i).getY(),ps.get(i).getDescripcion()));
        }

        return pcis;
    }

    @Override
    public ArrayList<Posicion> crear(ArrayList<Posicion> ps, Posicion p) {
        ps.add(p);
        System.out.println("Posición creada con éxito");
        return ps;
    }

    @Override
    public ArrayList<Posicion> actualizar(ArrayList<Posicion> ps, Posicion p, int id) {
        ps.set(id,p);
        return ps;
    }

    @Override
    public ArrayList<Posicion> borrar(ArrayList<Posicion> ps, int id) {
        ps.remove(id);
        return ps;
    }
}
