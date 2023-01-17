package com.example.ivan_fernandez_mendez_examen2.ui.Recetas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecetasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RecetasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}