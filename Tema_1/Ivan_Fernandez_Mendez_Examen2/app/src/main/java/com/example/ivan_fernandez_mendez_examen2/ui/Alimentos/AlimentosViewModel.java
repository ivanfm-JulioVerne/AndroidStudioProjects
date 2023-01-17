package com.example.ivan_fernandez_mendez_examen2.ui.Alimentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlimentosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlimentosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}