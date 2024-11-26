package com.example.ojtaadaassignment12.presenter.ui.movie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.usecase.GetMoviesUseCase;
import com.example.ojtaadaassignment12.domain.usecase.favorite.GetFavoriteMovieIdsUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class MovieListViewModel extends ViewModel {
    private final GetMoviesUseCase getMoviesUC;
    private final GetFavoriteMovieIdsUseCase getFavoriteMovieIdsUC;
//    private final GetFavoriteMoviesUseCase getFavoriteMoviesUseCase;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public MovieListViewModel(
            GetMoviesUseCase getMoviesUseCase,
            GetFavoriteMovieIdsUseCase getFavoriteMovieIdsUseCase) {
        this.getMoviesUC = getMoviesUseCase;
        this.getFavoriteMovieIdsUC = getFavoriteMovieIdsUseCase;
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchMovies(String apiKey) {
        isLoading.setValue(true);

        disposables.add(
                getMoviesUC.invoke("popular", apiKey, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(movies -> {
                            // Cập nhật moviesLiveData
                            moviesLiveData.setValue(movies);
                            return getFavoriteMovieIdsUC.execute();
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                favoriteMovieIds -> {
                                    List<Movie> listMovie = moviesLiveData.getValue();
                                    if (listMovie != null && favoriteMovieIds != null) {
                                        List<Movie> updatedMovies = new ArrayList<>();
                                        for (Movie movie : listMovie) {
                                            movie.setFavorite(favoriteMovieIds.contains(movie.getId()));
                                            updatedMovies.add(movie);
                                        }
                                        moviesLiveData.setValue(updatedMovies);
                                        Log.d("logd_viewmodel", updatedMovies.toString());
                                    }
                                },
                                throwable -> {
                                    isLoading.setValue(false);
                                    errorMessage.setValue("Error fetching data: " + throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}