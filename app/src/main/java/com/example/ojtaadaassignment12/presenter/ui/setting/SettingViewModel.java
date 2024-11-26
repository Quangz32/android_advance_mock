package com.example.ojtaadaassignment12.presenter.ui.setting;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.usecase.setting.GetSettingUseCase;
import com.example.ojtaadaassignment12.domain.usecase.setting.SaveSettingUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SettingViewModel extends ViewModel {

    private final GetSettingUseCase getSettingUseCase;
    private final SaveSettingUseCase saveSettingUseCase;

    private MutableLiveData<String> categoryLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> movieRateLiveData = new MutableLiveData<>();
    private MutableLiveData<String> releaseYearLiveData = new MutableLiveData<>();
    private MutableLiveData<String> sortByLiveData = new MutableLiveData<>();

    @Inject
    public SettingViewModel(GetSettingUseCase getSettingUseCase, SaveSettingUseCase saveSettingUseCase) {
        this.getSettingUseCase = getSettingUseCase;
        this.saveSettingUseCase = saveSettingUseCase;
    }

    public LiveData<String> getCategoryLiveData() {
        return categoryLiveData;
    }

    public LiveData<Integer> getMovieRateLiveData() {
        return movieRateLiveData;
    }

    public LiveData<String> getReleaseYearLiveData() {
        return releaseYearLiveData;
    }

    public LiveData<String> getSortByLiveData() {
        return sortByLiveData;
    }

    public void loadSettings() {
        categoryLiveData.setValue(getSettingUseCase.getCategory());
        movieRateLiveData.setValue(getSettingUseCase.getMovieRate());
        releaseYearLiveData.setValue(getSettingUseCase.getReleaseYear());
        sortByLiveData.setValue(getSettingUseCase.getSortBy());
    }

    public void saveCategory(String category) {
        Log.d("logd.category", category);
        saveSettingUseCase.saveCategory(category);
    }

    public void saveMovieRate(int rate) {
        Log.d("logd.rate", String.valueOf(rate));
        saveSettingUseCase.saveMovieRate(rate);
    }

    public void saveReleaseYear(String year) {
        Log.d("logd.year", year);
        saveSettingUseCase.saveReleaseYear(year);
    }

    public void saveSortBy(String sortBy) {
        Log.d("logd.sort", sortBy);
        saveSettingUseCase.saveSortBy(sortBy);
    }
}

