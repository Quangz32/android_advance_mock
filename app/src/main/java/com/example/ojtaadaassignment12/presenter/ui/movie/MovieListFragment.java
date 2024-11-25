package com.example.ojtaadaassignment12.presenter.ui.movie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.databinding.FragmentMovieListBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.usecase.GetMoviesUseCase;
import com.example.ojtaadaassignment12.presenter.adapter.MovieAdapter;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesFragment;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

@SuppressLint("NotifyDataSetChanged")
public class MovieListFragment extends Fragment {
//    @Inject
//    GetMoviesUseCase getMoviesUseCase;
    @Inject
    MovieListViewModel viewModel;

    @Inject
    FavoriteMoviesViewModel favoriteViewModel;

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
        fetchAndObserveViewModel();

    }

    private void setupRecyclerView() {
        adapter = new MovieAdapter(null, null, false);
        adapter.setMovieItemCallback(new MovieAdapter.MovieItemCallback() {
            @Override
            public void onMovieClicked(Movie movie, int position) {
                Log.d("qz.movie.clicked", movie.getTitle());
            }

            @Override
            public void onStarClicked(Movie movie, int position, boolean isFavorite) {
                Log.d("qz.star.clicked", movie.getTitle());
                if (isFavorite){
                    favoriteViewModel.removeFromFavorites(movie);
                }else{
                    favoriteViewModel.addToFavorites(movie);
                }
            }
        });

        binding.recyclerMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerMovies.setAdapter(adapter);
    }

    private void fetchAndObserveViewModel() {
        viewModel.fetchMovies("e7631ffcb8e766993e5ec0c1f4245f93");

        //Observe MovieListViewModel
        viewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), movies -> {
//            adapter = new MovieAdapter(movies, new ArrayList<>() , true);
            adapter.setMovies(movies);
//            adapter.notifyDataSetChanged();
            binding.recyclerMovies.setAdapter(adapter); //****
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            binding.textError.setVisibility(error != null ? View.VISIBLE : View.GONE);
            binding.textError.setText(error);
        });

        //Observe FavoriteMoviesViewModel
        favoriteViewModel.fetchFavoriteMovies();
        favoriteViewModel.getFavoriteMovies().observe(getViewLifecycleOwner(), movies -> {
            adapter.setFavoriteMovies(movies);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
