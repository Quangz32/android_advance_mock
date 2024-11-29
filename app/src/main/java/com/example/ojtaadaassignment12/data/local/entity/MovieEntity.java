package com.example.ojtaadaassignment12.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movies")
public class MovieEntity {
    @PrimaryKey
    private final int id;
    private final String title;
    private final String overview;
    private final String posterPath;
    private final String releaseDate;
    private final float voteAverage;
    private final boolean adult;

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
