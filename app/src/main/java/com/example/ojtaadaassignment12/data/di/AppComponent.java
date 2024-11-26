package com.example.ojtaadaassignment12.data.di;

import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie.MovieListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MovieListFragment fragment);

    void inject(FavoriteMoviesFragment fragment);
}