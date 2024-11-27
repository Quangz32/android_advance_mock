package com.example.ojtaadaassignment12.presenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojtaadaassignment12.databinding.ItemMovieBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;

public class MoviePagingAdapter extends PagingDataAdapter<Movie, RecyclerView.ViewHolder> {

    public MoviePagingAdapter() {
        super(Movie.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = ItemMovieBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            ((MovieViewHolder) holder).bindView(movie);
        }
    }

    // ViewHolder cho các mục Movie
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Movie movie) {
            // Gắn dữ liệu Movie vào View
            binding.setMovie(movie);
        }
    }
}