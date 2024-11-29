package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.model.CastAndCrew;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.usecase.detail.GetCastAndCrewUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
public class MovieDetailViewModel extends ViewModel {
    private final MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<CastAndCrew>> castAndCrewLiveData = new MutableLiveData<>();

    private final GetCastAndCrewUseCase getCastAndCrewUseCase;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public MovieDetailViewModel(
            GetCastAndCrewUseCase getCastAndCrewUseCase
    ) {
        this.getCastAndCrewUseCase = getCastAndCrewUseCase;
    }

    public MutableLiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }

    public void toggleFavorite() {
        Movie movie = movieLiveData.getValue();
        movie.setFavorite(!movie.isFavorite());
        movieLiveData.setValue(movie);
    }

    public void fetchCastAndCrew(int movieId, String apiKey) {
        disposables.add(
                getCastAndCrewUseCase.execute(movieId, apiKey)
                        .subscribe(castAndCrewLiveData::postValue)
        );
    }

    public MutableLiveData<List<CastAndCrew>> getCastAndCrewLiveData() {
        return castAndCrewLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
