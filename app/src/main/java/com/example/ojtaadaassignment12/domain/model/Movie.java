package com.example.ojtaadaassignment12.domain.model;

public class Movie {

    private final int id;
    private final String title;
    private final String overview;
    private final String posterPath;
    private final String releaseDate;
    private final float voteAverage;
    private final boolean adult;
//    private boolean isFavorite;

    public Movie(int id, String title, String overview, String posterPath, String releaseDate, float voteAverage, boolean adult) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.adult = adult;
//        this.isFavorite = isFavorite;
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

}