package com.example.examen_mariajose_valverde_ada.daos;

import androidx.lifecycle.LiveData;

import com.example.examen_mariajose_valverde_ada.modelo.Ruta;

import java.util.ArrayList;

public interface DaoFirestore {
    public LiveData<ArrayList<Ruta>> getRutas();
    public void addRuta(int longInicial,int latInicial,int rumbo,int distancia);
}
