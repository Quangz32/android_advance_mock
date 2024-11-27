package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.FragmentMovieDetailBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;

public class MovieDetailFragment extends Fragment {



    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMovieDetailBinding binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        binding.setMovie(new Movie(10, "Hello", "Long overview", "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg", "2020-11-11", 5.5f, false, true));
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}