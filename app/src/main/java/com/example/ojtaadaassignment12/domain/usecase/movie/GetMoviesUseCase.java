package com.example.ojtaadaassignment12.domain.usecase.movie;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetMoviesUseCase {

    private final MovieRepository movieRepository;

    @Inject
    public GetMoviesUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Single<List<Movie>> invoke(String category, String apiKey, int page) {
        return movieRepository.getMovies(category, apiKey, page);
    }
}