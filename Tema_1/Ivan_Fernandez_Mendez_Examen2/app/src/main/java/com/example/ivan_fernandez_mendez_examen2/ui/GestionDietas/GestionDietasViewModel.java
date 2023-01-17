package com.example.ivan_fernandez_mendez_examen2.ui.GestionDietas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionDietasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GestionDietasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}