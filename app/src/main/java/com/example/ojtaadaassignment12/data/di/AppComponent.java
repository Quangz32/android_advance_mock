package com.example.ojtaadaassignment12.data.di;

import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesFragment;
import com.example.ojtaadaassignment12.presenter.ui.main.MainActivity;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.container.ListAndDetailContainerFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail.MovieDetailFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.movie_list.MovieListFragment;
import com.example.ojtaadaassignment12.presenter.ui.setting.SettingFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        PreferencesModule.class,
        NetworkModule.class,
        RepositoryModule.class})

public interface AppComponent {
    void inject(MainActivity activity);

    void inject(SettingFragment fragment);

    void inject(MovieListFragment fragment);

    void inject(FavoriteMoviesFragment fragment);

    void inject(ListAndDetailContainerFragment fragment);

    void inject(MovieDetailFragment fragment);

}
