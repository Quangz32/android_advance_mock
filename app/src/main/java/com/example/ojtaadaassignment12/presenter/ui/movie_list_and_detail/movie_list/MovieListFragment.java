package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.movie_list;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.data.repository.MovieRepositoryPaging;
import com.example.ojtaadaassignment12.databinding.FragmentMovieListBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.SettingRepository;
import com.example.ojtaadaassignment12.presenter.adapter.MoviePagingAdapter;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesViewModel;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail.MovieDetailViewModel;
import com.example.ojtaadaassignment12.presenter.ui.setting.SettingViewModel;

import javax.inject.Inject;

@SuppressLint("NotifyDataSetChanged")
public class MovieListFragment extends Fragment {

    @Inject
    MovieListViewModel viewModel;

    @Inject
    FavoriteMoviesViewModel favoriteViewModel;

    @Inject
    SettingViewModel settingViewModel;

    @Inject
    MovieDetailViewModel movieDetailViewModel;

    @Inject
    MovieRepositoryPaging movieRepositoryPaging;

    @Inject
    SharedPreferences settingPreferences;

    @Inject
    SettingRepository settingRepository;

    //    private MovieListViewModel viewModel;
    private FragmentMovieListBinding binding;
    private MoviePagingAdapter moviePagingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inject dependencies
        ((App) requireActivity().getApplication()).getAppComponent().inject(this);
        setupRecyclerView();
        setupViewModel();
    }

    private void setupViewModel() {
//        viewModel.fetchMovies(settingViewModel.getCategoryLiveData().getValue());
//        Log.d("logd.category.setup", settingViewModel.getCategoryLiveData().getValue().toString());

        //Fetch movies khi mở app
//        viewModel.fetchMovies(settingPreferences.getString("category", "Popular"));
        viewModel.fetchMovies(settingRepository.getCategory());

        viewModel.getMoviePagingData().observe(getViewLifecycleOwner(), pagingData -> {
            moviePagingAdapter.submitData(getLifecycle(), pagingData);
        });


        //theo dõi thay đổi trong Setting
        settingViewModel.getCategoryLiveData().observe(getViewLifecycleOwner(),
                category -> {
                    Log.d("logd.category.obs", category);
                    viewModel.fetchMovies(category);
                }
        );

        settingViewModel.getMovieRateLiveData().observe(getViewLifecycleOwner(),
                movieRate -> {
                    viewModel.fetchMovies(settingViewModel.getCategoryLiveData().getValue());
                });

        settingViewModel.getReleaseYearLiveData().observe(getViewLifecycleOwner(),
                releaseYear -> {
                    viewModel.fetchMovies(settingViewModel.getCategoryLiveData().getValue());
                });

        settingViewModel.getSortByLiveData().observe(getViewLifecycleOwner(),
                sortBy -> {
                    viewModel.fetchMovies(settingViewModel.getCategoryLiveData().getValue());
                });

    }

    private void setupRecyclerView() {
        moviePagingAdapter = new MoviePagingAdapter();
        moviePagingAdapter.setMovieActionListener(new MoviePagingAdapter.MovieActionListener() {
            @Override
            public void onMovieClick(Movie movie, int position) {
                Log.d("logd.movie.click", movie.toString());

//                MovieDetailViewModel movieDetailViewModel =
//                        new ViewModelProvider(requireActivity()).get(MovieDetailViewModel.class);
                movieDetailViewModel.getMovieLiveData().setValue(movie);
                NavController navController = Navigation.findNavController(requireView());

                navController.navigate(R.id.movieDetailFragment);
            }

            @Override
            public void onStarClick(Movie movie, int position) {
                Log.d("logd.star.click", movie.toString());
                favoriteViewModel.toggleFavorite(movie); //Cập nhật trong DB
//                movie.setFavorite(!movie.isFavorite());
            }
        });

        binding.recyclerMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerMovies.setAdapter(moviePagingAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
