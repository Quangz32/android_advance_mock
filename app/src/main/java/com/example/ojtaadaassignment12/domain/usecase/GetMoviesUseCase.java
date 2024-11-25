package com.example.ojtaadaassignment12.domain.usecase;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetMoviesUseCase {

    private final MovieRepository movieRepository;

    @Inject
    public GetMoviesUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Observable<List<Movie>> invoke(String apiKey) {
        return movieRepository.getPopularMovies(apiKey);
    }
}