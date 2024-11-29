package com.example.ojtaadaassignment12.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.ojtaadaassignment12.data.local.dao.MovieDao;
import com.example.ojtaadaassignment12.data.local.database.AppDatabase;
import com.example.ojtaadaassignment12.data.mapper.CastAndCrewMapper;
import com.example.ojtaadaassignment12.data.mapper.MovieMapper;
import com.example.ojtaadaassignment12.data.remote.api.CastAndCrewApi;
import com.example.ojtaadaassignment12.data.remote.api.MovieApi;
import com.example.ojtaadaassignment12.data.repository.CastAndCrewRepositoryImpl;
import com.example.ojtaadaassignment12.data.repository.FavoriteMovieRepositoryImpl;
import com.example.ojtaadaassignment12.data.repository.MovieRepositoryImpl;
import com.example.ojtaadaassignment12.data.repository.SettingRepositoryImpl;
import com.example.ojtaadaassignment12.domain.repository.CastAndCrewRepository;
import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;
import com.example.ojtaadaassignment12.domain.repository.SettingRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public MovieMapper provideMovieMapper() {
        return new MovieMapper();
    }

    @Singleton
    @Provides
    public CastAndCrewMapper provideCastAndCrewMapper() {
        return new CastAndCrewMapper();
    }

    @Singleton
    @Provides
    public MovieDao provideMovieDao(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "movie_database").build().movieDao();
    }

    @Singleton
    @Provides
    public MovieRepository provideMovieRepository(MovieApi movieApi, MovieMapper movieMapper) {
        return new MovieRepositoryImpl(movieApi, movieMapper);
    }

    @Singleton
    @Provides
    public FavoriteMovieRepository provideFavoriteMovieRepository(MovieDao movieDao, MovieMapper movieMapper) {
        return new FavoriteMovieRepositoryImpl(movieDao, movieMapper);
    }

    @Singleton
    @Provides
    public SettingRepository provideSettingRepository(Context context) {
        return new SettingRepositoryImpl(context);
    }

    @Singleton
    @Provides
    public CastAndCrewRepository provideCastAndCrewRepository(
            CastAndCrewApi api, CastAndCrewMapper mapper
    ) {
        return new CastAndCrewRepositoryImpl(api, mapper);
    }
}

