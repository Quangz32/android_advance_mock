package com.example.ojtaadaassignment12.data.repository;

import com.example.ojtaadaassignment12.data.mapper.MovieMapper;
import com.example.ojtaadaassignment12.data.remote.api.MovieApi;
import com.example.ojtaadaassignment12.data.remote.dto.MovieDto;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class MovieRepositoryImpl implements MovieRepository {

    private final MovieApi movieApi;    //Dùng để load Movie từ API
    private final MovieMapper movieMapper;  //Dùng để map Movie từ DTO sang Domain Model
//    private final MovieDao movieDao;    //Dùng để tìm Movie yêu thích

    @Inject
    public MovieRepositoryImpl(MovieApi movieApi, MovieMapper movieMapper) {
        this.movieApi = movieApi;
        this.movieMapper = movieMapper;
    }

    @Override
    public Observable<List<Movie>> getMovies(String category, String apiKey, int page) {
        return movieApi.getMovies(category, apiKey, page)
                .map(response -> {
                    List<MovieDto> movieDtos = response.getResults();
                    return movieMapper.mapDtoListToDomainList(movieDtos);
                });
    }
}