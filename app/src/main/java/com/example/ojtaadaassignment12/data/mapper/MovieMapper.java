package com.example.ojtaadaassignment12.data.mapper;

import com.example.ojtaadaassignment12.data.local.dao.MovieDao;
import com.example.ojtaadaassignment12.data.local.entity.MovieEntity;
import com.example.ojtaadaassignment12.data.remote.dto.MovieDto;
import com.example.ojtaadaassignment12.domain.model.Movie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class MovieMapper {
    @Inject
    MovieDao movieDao;

    public Movie mapToDomain(MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getOverview(),
                movieDto.getPosterPath(),
                movieDto.getReleaseDate(),
                movieDto.getVoteAverage(),
                movieDto.isAdult(),
                false
        );
    }

    public Movie mapToDomain(MovieEntity entity) {
        return new Movie(
                entity.getId(),
                entity.getTitle(),
                entity.getOverview(),
                entity.getPosterPath(),
                entity.getReleaseDate(),
                entity.getVoteAverage(),
                entity.isAdult(),
                true //Mặc định là yêu thích khi load từ DB
        );
    }

    public Observable<List<Movie>> mapFromResponse(List<MovieDto> movieDtos) {
        return movieDao.getFavoriteMovieIds()
                .map(HashSet::new) // Chuyển List thành Set
                .map(favoriteMovieIds -> {
                    List<Movie> movies = new ArrayList<>();
                    for (MovieDto dto : movieDtos) {
                        movies.add(new Movie(
                                dto.getId(),
                                dto.getTitle(),
                                dto.getOverview(),
                                dto.getPosterPath(),
                                dto.getReleaseDate(),
                                dto.getVoteAverage(),
                                dto.isAdult(),
                                favoriteMovieIds.contains(dto.getId()) // Đánh dấu yêu thích
                        ));
                    }
                    return movies;
                });
    }

    public List<Movie> mapDtoListToDomainList(List<MovieDto> dtos) {
        List<Movie> movies = new ArrayList<>();
        for (MovieDto dto : dtos) {
            movies.add(mapToDomain(dto));
        }
        return movies;

    }


    public List<Movie> mapEntityListToDomainList(List<MovieEntity> entities) {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity entity : entities) {
            movies.add(mapToDomain(entity));
        }
        return movies;
    }


    public MovieEntity mapToEntity(Movie movie) {
        return new MovieEntity(
                movie.getId(),
                movie.getTitle(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.getReleaseDate(),
                movie.getVoteAverage(),
                movie.isAdult()
        );
    }
}