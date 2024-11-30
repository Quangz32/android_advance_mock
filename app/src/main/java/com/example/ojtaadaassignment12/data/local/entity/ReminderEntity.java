package com.example.ojtaadaassignment12.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminders")
public class ReminderEntity {
    @PrimaryKey
    private int id;

    private String movieTitle;
    private String movieReleaseDate;
    private float movieRating;
    private String moviePoster;
    private long timestamp;

    public ReminderEntity(int id, String movieTitle, String movieReleaseDate, float movieRating, String moviePoster, long timestamp) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.movieReleaseDate = movieReleaseDate;
        this.movieRating = movieRating;
        this.moviePoster = moviePoster;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }
}
