package com.example.ojtaadaassignment12.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<MovieDto> results;

    public List<MovieDto> getResults() {
        return results;
    }
}