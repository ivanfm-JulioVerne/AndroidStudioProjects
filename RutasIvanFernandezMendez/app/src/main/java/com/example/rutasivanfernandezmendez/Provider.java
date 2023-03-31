package com.example.rutasivanfernandezmendez;

import com.example.rutasivanfernandezmendez.modelo.Lugar;

import java.util.ArrayList;

public class Provider {

    private ArrayList<Lugar> lugares;

    private float distTotal;

    public Provider() {
        lugares=new ArrayList<Lugar>();

        lugares.add(new Lugar("Huelva",200.12f));
        lugares.add(new Lugar("Sevilla",54f));
        lugares.add(new Lugar("Cádiz",63.8f));
        lugares.add(new Lugar("Córdoba",63.5f));
        lugares.add(new Lugar("Jaén",300.2f));
        lugares.add(new Lugar("Málaga",333.3f));
        lugares.add(new Lugar("Granada",696.3f));
        lugares.add(new Lugar("Almería",707.25f));

        distTotal=200.12f+54f+63.8f+63.5f+300.2f+333.3f+696+707.25f;
    }

    public ArrayList<Lugar> getLugares() {
        return lugares;
    }
    public void addLugar(Lugar lugar){
        lugares.add(lugar);
        distTotal=distTotal+lugar.getKms();
    }
    public void deleteLugar(int position){
        Lugar lugar=lugares.get(position);
        lugares.remove(position);
        distTotal=distTotal-lugar.getKms();
    }

    public float getDistTotal() {
        return distTotal;
    }
}
