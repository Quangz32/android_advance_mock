package com.example.ojtaadaassignment12.presenter.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojtaadaassignment12.databinding.ItemMovieBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.presenter.binding.BindingAdapters;

public class MoviePagingAdapter extends PagingDataAdapter<Movie, RecyclerView.ViewHolder> {

    private MovieActionListener listener;

    public MoviePagingAdapter() {
        super(Movie.DIFF_CALLBACK);
    }

    public void setMovieActionListener(MovieActionListener callBack) {
        this.listener = callBack;
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
            ((MovieViewHolder) holder).bindView(movie, position);
        }
    }

    public interface MovieActionListener {
        void onMovieClick(Movie movie, int position);

        void onStarClick(Movie movie, int position);
    }

    // ViewHolder cho các mục Movie
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Movie movie, int position) {
            // Gắn dữ liệu Movie vào View
            binding.setMovie(movie);

            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMovieClick(movie, position);
                }
            });

            binding.imgStar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onStarClick(movie, position);
                }

                movie.setFavorite(!movie.isFavorite());
                BindingAdapters.setCustomStarTint(binding.imgStar, movie.isFavorite());

                Log.d("logd.adapter.star.click", movie.toString());
//                movie.setFavorite(!movie.isFavorite());

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