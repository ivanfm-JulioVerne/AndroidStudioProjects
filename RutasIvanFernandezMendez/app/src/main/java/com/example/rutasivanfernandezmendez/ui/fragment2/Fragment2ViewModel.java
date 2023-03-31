package com.example.rutasivanfernandezmendez.ui.fragment2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment2ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Fragment2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}