package com.example.ojtaadaassignment12.presenter.ui.favourite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.databinding.FragmentFavoriteMoviesBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.presenter.adapter.MovieAdapter;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.movie_list.MovieListViewModel;

import javax.inject.Inject;

public class FavoriteMoviesFragment extends Fragment {
    FragmentFavoriteMoviesBinding binding;
    MovieAdapter adapter;

    @Inject
    FavoriteMoviesViewModel viewModel;

    @Inject
    MovieListViewModel movieListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inject dependencies
        ((App) requireActivity().getApplication()).getAppComponent().inject(this);

        setupRecyclerView();
        fetchAndObserveViewModel();

        //Test thêm Favorite Movie
//        viewModel.addToFavorites(
//                new Movie(8, "Movie 1", "Description 1", "https://example.com", "2020-10-10", 5.0f, true));
    }

    private void setupRecyclerView() {
        adapter = new MovieAdapter(null, true);
        adapter.setMovieItemCallback(new MovieAdapter.MovieItemCallback() {
            @Override
            public void onMovieClicked(Movie movie, int position) {
//                adapter.notifyItemChanged(position);
                Log.d("qz.movie.clicked", movie.getTitle());
            }

            @Override
            public void onStarClicked(Movie movie, int position) {
                adapter.notifyItemChanged(position);
                Log.d("qz.star.clicked", movie.getTitle());
                viewModel.removeFromFavorites(movie);
                adapter.notifyItemRemoved(position);

                movieListViewModel.fetchMovies();   //Cho List fetch lai Movie
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchAndObserveViewModel() {
        viewModel.fetchFavoriteMovies();
        viewModel.getFavoriteMovies().observe(getViewLifecycleOwner(), movies -> {
            adapter.setMovies(movies);
            adapter.notifyDataSetChanged();
        });
    }


}