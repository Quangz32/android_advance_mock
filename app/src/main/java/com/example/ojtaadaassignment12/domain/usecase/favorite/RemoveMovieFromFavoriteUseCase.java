package com.example.ojtaadaassignment12.domain.usecase.favorite;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class RemoveMovieFromFavoriteUseCase {

    private final FavoriteMovieRepository favoriteMovieRepository;

    @Inject
    public RemoveMovieFromFavoriteUseCase(FavoriteMovieRepository favoriteMovieRepository) {
        this.favoriteMovieRepository = favoriteMovieRepository;
    }

    public Completable execute(Movie movie) {
        return favoriteMovieRepository.removeMovieFromFavorite(movie);
    }
}
