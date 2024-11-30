package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

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
import com.example.ojtaadaassignment12.databinding.FragmentMovieDetailBinding;
import com.example.ojtaadaassignment12.presenter.binding.BindingAdapters;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesViewModel;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment {
    FragmentMovieDetailBinding binding;

    @Inject
    MovieDetailViewModel viewModel;

    @Inject
    FavoriteMoviesViewModel favoriteMoviesViewModel;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ((App) requireActivity().getApplication()).getAppComponent().inject(this);

//        viewModel = new ViewModelProvider(requireActivity()).get(MovieDetailViewModel.class); //?? Singleton
//        favoriteMoviesViewModel = new ViewModelProvider(requireActivity()).get(FavoriteMoviesViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
//        binding.setMovie(new Movie(10, "Hello", "Long overview", "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg", "2020-11-11", 5.5f, false, true));
        binding.setLifecycleOwner(this);

        binding.imgStar.setOnClickListener(v -> {
            favoriteMoviesViewModel.toggleFavorite(binding.getMovie());

            binding.getMovie().setFavorite(!binding.getMovie().isFavorite());
            BindingAdapters.setCustomStarTint(binding.imgStar, binding.getMovie().isFavorite());
//            Movie movie = viewModel.getMovieLiveData().getValue();
//            viewModel.toggleFavorite();
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getMovieLiveData().observe(getViewLifecycleOwner(), movie -> {
            binding.setMovie(movie);
            viewModel.fetchCastAndCrew(movie.getId(), "e7631ffcb8e766993e5ec0c1f4245f93");
        });

        viewModel.getCastAndCrewLiveData().observe(getViewLifecycleOwner(), castAndCrews -> {
//            binding.setCastAndCrews(castAndCrews);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            binding.recyclerView.setAdapter(new CastAndCrewAdapter(castAndCrews));
            Log.d("log.cast.crew", castAndCrews.toString());
        });

//        viewModel.fetchCastAndCrew();


    }

}