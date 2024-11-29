package com.example.ojtaadaassignment12.data.remote.api;

import com.example.ojtaadaassignment12.data.remote.dto.CastAndCrewResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastAndCrewApi {
//    @GET("movie/{category}")
//    Single<MovieResponse> getMovies(@Path("category") String category, @Query("api_key") String apiKey, @Query("page") int pageNumber);

    @GET("movie/{movieId}/credits")
    Single<CastAndCrewResponse> getCastAndCrews(@Path("movieId") int movieId, @Query("api_key") String apiKey);
}

