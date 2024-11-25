package com.example.ojtaadaassignment12.domain.repository;

import com.example.ojtaadaassignment12.domain.model.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface FavoriteMovieRepository {
    Observable<List<Movie>> getFavoriteMovies();
    Completable addMovieToFavorite(Movie movie);
    Completable removeMovieFromFavorite(Movie movie);
}
