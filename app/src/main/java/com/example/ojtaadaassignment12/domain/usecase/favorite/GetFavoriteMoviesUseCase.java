package com.example.ojtaadaassignment12.domain.usecase.favorite;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetFavoriteMoviesUseCase {

    private final FavoriteMovieRepository favoriteMovieRepository;

    @Inject
    public GetFavoriteMoviesUseCase(FavoriteMovieRepository favoriteMovieRepository) {
        this.favoriteMovieRepository = favoriteMovieRepository;
    }

    public Observable<List<Movie>> execute() {
        return favoriteMovieRepository.getFavoriteMovies();
    }
}
