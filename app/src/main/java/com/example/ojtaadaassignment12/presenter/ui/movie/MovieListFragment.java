package com.example.ojtaadaassignment12.presenter.ui.movie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.data.repository.MovieRepositoryPaging;
import com.example.ojtaadaassignment12.databinding.FragmentMovieListBinding;
import com.example.ojtaadaassignment12.presenter.adapter.MovieAdapter;
import com.example.ojtaadaassignment12.presenter.adapter.MoviePagingAdapter;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesViewModel;
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
    MovieRepositoryPaging movieRepositoryPaging;

    //    private MovieListViewModel viewModel;
    private FragmentMovieListBinding binding;
    private MovieAdapter adapter;

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
    }

    private void setupRecyclerView() {
        MoviePagingAdapter moviePagingAdapter = new MoviePagingAdapter();
        binding.recyclerMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getMoviePagingData().observe(this, pagingData -> {
            moviePagingAdapter.submitData(getLifecycle(), pagingData);
        });
        binding.recyclerMovies.setAdapter(moviePagingAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
