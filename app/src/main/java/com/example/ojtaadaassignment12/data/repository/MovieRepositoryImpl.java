package com.example.ojtaadaassignment12.data.repository;

import com.example.ojtaadaassignment12.data.mapper.MovieMapper;
import com.example.ojtaadaassignment12.data.remote.api.MovieApi;
import com.example.ojtaadaassignment12.data.remote.dto.MovieDto;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MovieRepositoryImpl implements MovieRepository {

    private final MovieApi movieApi;
    private final MovieMapper movieMapper;

    public MovieRepositoryImpl(MovieApi movieApi, MovieMapper movieMapper) {
        this.movieApi = movieApi;
        this.movieMapper = movieMapper;
    }

    @Override
    public Observable<List<Movie>> getPopularMovies(String apiKey) {
        return movieApi.getPopularMovies(apiKey)
                .map(movieResponse -> {
                    List<MovieDto> movieDtos = movieResponse.getResults();
                    return movieMapper.mapFromResponse(movieDtos);
                });
    }



}