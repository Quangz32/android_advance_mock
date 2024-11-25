package com.example.ojtaadaassignment12.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movies")
public class MovieEntity {
    @PrimaryKey
    private int id;
    private String title;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private float voteAverage;
    private boolean adult;

    public MovieEntity(int id, String title, String overview, String posterPath, String releaseDate,
                       float voteAverage, boolean adult) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.adult = adult;
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
