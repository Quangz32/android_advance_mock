package com.example.ojtaadaassignment12.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ojtaadaassignment12.data.local.entity.MovieEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MovieDao {
    @Insert
    void insertMovie(MovieEntity movieEntity);

    @Delete
    void deleteMovie(MovieEntity movieEntity);

    @Query("SELECT * FROM favorite_movies")
    Observable<List<MovieEntity>> getAllFavoriteMovies();

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId LIMIT 1")
    Observable<MovieEntity> getFavoriteMovieById(int movieId);

    @Query("SELECT id FROM favorite_movies")
    Observable<List<Integer>> getFavoriteMovieIds();
}
