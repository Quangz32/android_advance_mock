package com.example.ojtaadaassignment12.domain.model;

import java.io.Serializable;

public class Reminder implements Serializable {
    private int id;
    private String movieTitle;
    private String movieReleaseDate;
    private float movieRating;
    private String moviePoster;
    private long timestamp;

    public Reminder(int id, String movieTitle, String movieReleaseDate, float movieRating, String moviePoster, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
        this.movieRating = movieRating;
        this.movieReleaseDate = movieReleaseDate;
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public float getMovieRating() {
        return movieRating;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieReleaseYear='" + movieReleaseDate + '\'' +
                ", movieRating=" + movieRating +
                ", moviePoster='" + moviePoster + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}