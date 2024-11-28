package com.example.ojtaadaassignment12.presenter.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainViewModel extends ViewModel {
    public MutableLiveData<Boolean> tab1ShowingDetail = new MutableLiveData<>(false);

    @Inject
    public MainViewModel() {
    }

    public MutableLiveData<Boolean> getTab1ShowingDetail() {
        return tab1ShowingDetail;
    }


}
