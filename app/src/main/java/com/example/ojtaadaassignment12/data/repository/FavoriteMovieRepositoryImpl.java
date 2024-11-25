package com.example.ojtaadaassignment12.data.repository;

import android.util.Log;

import com.example.ojtaadaassignment12.data.local.dao.MovieDao;
import com.example.ojtaadaassignment12.data.mapper.MovieMapper;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMovieRepositoryImpl implements FavoriteMovieRepository {

    private final MovieDao movieDao;
    private final MovieMapper movieMapper;

    @Inject
    public FavoriteMovieRepositoryImpl(MovieDao movieDao, MovieMapper movieMapper) {
        this.movieDao = movieDao;
        this.movieMapper = movieMapper;
    }

    @Override
    public Observable<List<Movie>> getFavoriteMovies() {
        return movieDao.getAllFavoriteMovies()
                .subscribeOn(Schedulers.io())
                .map(movieEntities -> {
                    Log.d("qzFavoriteMovieRepository", "Movies in database: " + movieEntities.size());
                    return movieMapper.mapToDomainList(movieEntities);
                });
    }

    @Override
    public Completable addMovieToFavorite(Movie movie) {
        return Completable.fromAction(() -> movieDao.insertMovie(movieMapper.mapToEntity(movie)))
                .subscribeOn(Schedulers.io());  // Thực thi trên background thread
    }

    @Override
    public Completable removeMovieFromFavorite(Movie movie) {
        return Completable.fromAction(() -> movieDao.deleteMovie(movieMapper.mapToEntity(movie)))
                .subscribeOn(Schedulers.io());  // Thực thi trên background thread
    }

}
