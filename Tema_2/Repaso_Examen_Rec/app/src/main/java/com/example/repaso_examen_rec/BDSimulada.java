package com.example.repaso_examen_rec;

import android.util.Log;

import com.example.repaso_examen_rec.modelo.Alimento;

import java.util.ArrayList;

public class BDSimulada {
    private ArrayList<Alimento> alimentos;

    public BDSimulada(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public BDSimulada() {
        alimentos=new ArrayList<Alimento>();

        alimentos.add(new Alimento("Pan",23));
        alimentos.add(new Alimento("Tomate",12));
        alimentos.add(new Alimento("Queso",50));
        alimentos.add(new Alimento("Jamón York",200));
        alimentos.add(new Alimento("Chorizo",180));
        alimentos.add(new Alimento("Aguacate",40));
    }

    public void addAlimento(Alimento alimento){
        alimentos.add(alimento);
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void deleteAlimento(int position){
        Log.d(":::Borra",position+"");
        alimentos.remove(position);
    }

    public Alimento getAlimento(int position){
        return alimentos.get(position);
    }
}
