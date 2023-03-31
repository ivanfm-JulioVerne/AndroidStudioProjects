package com.example.rutasivanfernandezmendez.ui.fragment1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Fragment1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}