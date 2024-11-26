package com.example.ojtaadaassignment12.presenter.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojtaadaassignment12.databinding.ItemMovieBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;

import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    //    private List<Movie> favoriteMovies;
    private boolean favoriteMode;   //Tất cả movie đều là Yêu thích

//    private int currentPosition;

    private MovieItemCallback callback;

    public MovieAdapter(List<Movie> movies, boolean favoriteMode) {
        this.movies = movies;
//        this.favoriteMovies = favoriteMovies;
        this.favoriteMode = favoriteMode;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        Log.d("logd_adapter", "movie list set: " + movies.toString());
    }

//    public void setFavoriteMovies(List<Movie> favoriteMovies) {
//        if (movies != null){
//            List<Integer> changedPositions = new ArrayList<>();
//            for (int i = 0; i < movies.size(); i++){
//                Movie movie = movies.get(i);
//                if (isFavoriteMovie(movie, favoriteMovies) != isFavoriteMovie(movie, this.favoriteMovies)){
//                    changedPositions.add(i);
//                }
//            }
//            for (int position : changedPositions){
//                notifyItemChanged(position);
//            }
//        }
//
//        this.favoriteMovies = favoriteMovies;
//
////        notifyDataSetChanged();
//    }

//    public void setFavoriteMode(boolean favoriteMode) {
//        this.favoriteMode = favoriteMode;
//        notifyDataSetChanged();
//    }

    public void setMovieItemCallback(MovieItemCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding binding = ItemMovieBinding.inflate(layoutInflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie, position);
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public interface MovieItemCallback {
        void onMovieClicked(Movie movie, int position);

        void onStarClicked(Movie movie, int position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie, int position) {
            binding.setMovie(movie);

            binding.getRoot().setOnClickListener(v -> {
                if (callback != null) {
                    callback.onMovieClicked(movie, position);
                }
            });

            binding.imgStar.setOnClickListener(v -> {
                if (callback != null) {
                    callback.onStarClicked(movie, position);
                }
//                isFavorite = !isFavorite;
//                binding.setIsFavourite(isFavorite);
            });
        }
    }
}