package com.example.ojtaadaassignment12.data.remote.api;

import com.example.ojtaadaassignment12.data.remote.dto.MovieResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
}