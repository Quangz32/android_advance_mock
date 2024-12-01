package com.example.ojtaadaassignment12.presenter.adapter;

import com.example.ojtaadaassignment12.domain.model.Movie;

public interface MovieItemCallback {
    void onMovieClick(Movie movie, int position);

    void onStarClick(Movie movie, int position);
}
