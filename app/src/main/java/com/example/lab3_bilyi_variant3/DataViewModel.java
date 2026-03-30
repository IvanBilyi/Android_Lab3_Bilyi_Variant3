package com.example.lab3_bilyi_variant3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    public final MutableLiveData<String> inputText = new MutableLiveData<>();
    public final MutableLiveData<Integer> selectedFont = new MutableLiveData<>();
    public final MutableLiveData<Boolean> clearFormSignal = new MutableLiveData<>();
}
