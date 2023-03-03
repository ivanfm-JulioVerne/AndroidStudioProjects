package com.example.ivan_fernandez_mendez.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<Double> longIni;
    private final MutableLiveData<Double> latIni;
    private final MutableLiveData<Double> rumbo;
    private final MutableLiveData<Double> distancia;

    public GalleryViewModel() {
        longIni = new MutableLiveData<>();
        longIni.setValue(5.0);

        latIni = new MutableLiveData<>();
        latIni.setValue(5.0);

        rumbo = new MutableLiveData<>();
        rumbo.setValue(5.0);

        distancia = new MutableLiveData<>();
        distancia.setValue(5.0);
    }

    public LiveData<Double> longIni() {
        return longIni;
    }
    public LiveData<Double> latIni() {
        return longIni;
    }
    public LiveData<Double> rumbo() {
        return longIni;
    }
    public LiveData<Double> distancia() {
        return longIni;
    }
}