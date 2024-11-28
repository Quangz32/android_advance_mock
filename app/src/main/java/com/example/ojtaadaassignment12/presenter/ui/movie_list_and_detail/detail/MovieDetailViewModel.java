package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.model.Movie;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieDetailViewModel extends ViewModel {
    private MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();

    @Inject
    public MovieDetailViewModel() {
    }

    public MutableLiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
