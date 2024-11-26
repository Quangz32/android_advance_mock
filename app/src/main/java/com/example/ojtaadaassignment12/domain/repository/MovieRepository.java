package com.example.ojtaadaassignment12.domain.repository;

import com.example.ojtaadaassignment12.domain.model.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MovieRepository {
    Observable<List<Movie>> getMovies(String category, String apiKey, int page);
}