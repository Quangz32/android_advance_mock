package com.example.ojtaadaassignment12.presenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojtaadaassignment12.databinding.ItemMovieGridBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;

public class MoviePagingGridAdapter extends PagingDataAdapter<Movie, RecyclerView.ViewHolder> {

    private MovieItemCallback movieItemCallback;

    public MoviePagingGridAdapter() {
        super(Movie.DIFF_CALLBACK);
    }

    public void setMovieItemCallback(MovieItemCallback movieItemCallback) {
        this.movieItemCallback = movieItemCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        ItemMovieBinding binding = ItemMovieBinding.inflate(layoutInflater, parent, false);
        ItemMovieGridBinding binding = ItemMovieGridBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            ((MovieViewHolder) holder).bindView(movie, position);
        }
    }

    // ViewHolder cho các mục Movie
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        //        private final ItemMovieBinding binding;
        private final ItemMovieGridBinding binding;

        public MovieViewHolder(ItemMovieGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Movie movie, int position) {
            // Gắn dữ liệu Movie vào View
//            binding.setMovie(movie);
            binding.setMovie(movie);

            binding.getRoot().setOnClickListener(v -> {
                if (movieItemCallback != null) {
                    movieItemCallback.onMovieClick(movie, position);
                }
            });
        }
    }

//    private void removeFavoriteByMovieId(int movieId){
//        for (int i= 0 ;i < getItemCount(); i++){
//            Movie movie = getItem(i);
//            if (movie != null && movie.getId() == movieId){
//                movie.setFavorite(false);
//                notifyItemChanged(i);
//            }
//        }
//    }
}