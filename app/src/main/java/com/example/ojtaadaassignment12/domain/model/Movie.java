package com.example.ojtaadaassignment12.domain.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Movie {

    // Khai báo DIFF_CALLBACK giúp DiffUtil xác định sự khác biệt giữa hai đối tượng Movie
    public static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
            return oldMovie.areContentsTheSame(newMovie);
        }
    };
    private final int id;
    private final String title;
    private final String overview;
    private final String posterPath;
    private final String releaseDate;
    private final float voteAverage;
    private final boolean adult;
    private boolean isFavorite;

    public Movie(int id, String title, String overview, String posterPath, String releaseDate,
                 float voteAverage, boolean adult, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.adult = adult;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
//                ", overview='" + overview + '\'' +
//                ", posterPath='" + posterPath + '\'' +
//                ", releaseDate='" + releaseDate + '\'' +
//                ", voteAverage=" + voteAverage +
//                ", adult=" + adult +
                ", isFavorite=" + isFavorite +
                '}';
    }

    public boolean areContentsTheSame(Movie movieToCompare) {
        return this.adult == movieToCompare.adult &&
                this.title.equals(movieToCompare.title) &&
                this.posterPath.equals(movieToCompare.posterPath) &&
                this.overview.equals(movieToCompare.overview) &&
                this.releaseDate.equals(movieToCompare.releaseDate) &&
                this.voteAverage == movieToCompare.voteAverage &&
                this.isFavorite == movieToCompare.isFavorite;

    }

    @NonNull
    public Movie getClone(){
        return new Movie(id, title, overview, posterPath, releaseDate, voteAverage, adult, isFavorite);
    }
}