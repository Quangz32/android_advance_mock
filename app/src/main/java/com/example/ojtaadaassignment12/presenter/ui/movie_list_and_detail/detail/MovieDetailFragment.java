package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ojtaadaassignment12.databinding.FragmentMovieDetailBinding;

public class MovieDetailFragment extends Fragment {

    MovieDetailViewModel viewModel;
    FragmentMovieDetailBinding binding;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MovieDetailViewModel.class); //?? Singleton

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
//        binding.setMovie(new Movie(10, "Hello", "Long overview", "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg", "2020-11-11", 5.5f, false, true));
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getMovieLiveData().observe(getViewLifecycleOwner(), binding::setMovie);
    }

}