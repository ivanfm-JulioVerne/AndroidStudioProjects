package com.example.examen_mariajose_valverde_ada.ui.gallery;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen_mariajose_valverde_ada.conexionFirestore.UsoFirestore;
import com.example.examen_mariajose_valverde_ada.daos.DaoFirestore;
import com.example.examen_mariajose_valverde_ada.modelo.Ruta;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GalleryViewModel extends ViewModel implements DaoFirestore {

    private final MutableLiveData< String > mText;

    private MutableLiveData<ArrayList<Ruta>> rutas;

    private FirebaseFirestore db;
    private UsoFirestore uf;
    public GalleryViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

        rutas=new MutableLiveData<>();
        rutas.setValue(new ArrayList<Ruta>());
        uf=new UsoFirestore();
        db=uf.getDb();
        rutas=uf.getRutas(db);
    }

    /*public LiveData<String> getText() {
        return mText;
    }*/
    @Override
    public LiveData<ArrayList<Ruta>> getRutas(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(()->{

           rutas=uf.getRutas(db);
        });
        return rutas;}
    @Override
    public void addRuta(int longInicial,int latInicial,int rumbo,int distancia){

        ArrayList<Ruta> ruta=rutas.getValue();

        ruta.add(new Ruta(longInicial,latInicial,rumbo,distancia));
        rutas.postValue(ruta);
        uf.addRuta(db,new Ruta(longInicial,latInicial,rumbo,distancia));

    }
}