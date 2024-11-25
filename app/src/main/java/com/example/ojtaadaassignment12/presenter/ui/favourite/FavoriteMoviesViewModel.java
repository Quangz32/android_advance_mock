package com.example.ojtaadaassignment12.presenter.ui.favourite;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.usecase.GetFavoriteMoviesUseCase;
import com.example.ojtaadaassignment12.domain.usecase.AddMovieToFavoriteUseCase;
import com.example.ojtaadaassignment12.domain.usecase.RemoveMovieFromFavoriteUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
public class FavoriteMoviesViewModel extends ViewModel {

    private final GetFavoriteMoviesUseCase getFavoriteMoviesUseCase;
    private final AddMovieToFavoriteUseCase addMovieToFavoriteUseCase;
    private final RemoveMovieFromFavoriteUseCase removeMovieFromFavoriteUseCase;

    private final MutableLiveData<List<Movie>> favoriteMovies = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public FavoriteMoviesViewModel(GetFavoriteMoviesUseCase getFavoriteMoviesUseCase,
                                   AddMovieToFavoriteUseCase addMovieToFavoriteUseCase,
                                   RemoveMovieFromFavoriteUseCase removeMovieFromFavoriteUseCase) {
        this.getFavoriteMoviesUseCase = getFavoriteMoviesUseCase;
        this.addMovieToFavoriteUseCase = addMovieToFavoriteUseCase;
        this.removeMovieFromFavoriteUseCase = removeMovieFromFavoriteUseCase;
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void fetchFavoriteMovies() {
        disposables.add(
                getFavoriteMoviesUseCase.execute()
                        .doOnNext(movies -> Log.d("qzFavoriteMoviesViewModel", "Fetched movies: " + movies.size()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                favoriteMovies::postValue,
                                throwable -> Log.e("qzFavoriteMoviesViewModel", throwable.toString())
                        )
        );
    }


    public void addToFavorites(Movie movie) {
        disposables.add(
                addMovieToFavoriteUseCase.execute(movie)
                        .doOnComplete(() -> Log.d("qzFavoriteMoviesViewModel", "Movie added to favorites: " + movie.getTitle()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::fetchFavoriteMovies,
                                throwable -> Log.e("qzFavoriteMoviesViewModel", throwable.toString())
                        )
        );
    }


    public void removeFromFavorites(Movie movie) {
        disposables.add(
                removeMovieFromFavoriteUseCase.execute(movie)
                        .observeOn(AndroidSchedulers.mainThread()) // Đưa kết quả lên Main thread
                        .subscribe(
                                this::fetchFavoriteMovies, // Tự động tải lại danh sách yêu thích
                                Throwable::printStackTrace
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
