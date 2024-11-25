package com.example.ojtaadaassignment12.data.mapper;

import com.example.ojtaadaassignment12.data.local.entity.MovieEntity;
import com.example.ojtaadaassignment12.data.remote.dto.MovieDto;
import com.example.ojtaadaassignment12.domain.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public List<Movie> mapFromResponse(List<MovieDto> movieDtos) {
        List<Movie> movies = new ArrayList<>();
        for (MovieDto dto : movieDtos) {
            movies.add(new Movie(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getOverview(),
                    dto.getPosterPath(),
                    dto.getReleaseDate(),
                    dto.getVoteAverage(),
                    dto.isAdult()
            ));
        }
        return movies;
    }

    public List<Movie> mapToDomainList(List<MovieEntity> entities) {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity entity : entities) {
            movies.add(mapToDomain(entity));
        }
        return movies;
    }

    public Movie mapToDomain(MovieEntity entity) {
        return new Movie(
                entity.getId(),
                entity.getTitle(),
                entity.getOverview(),
                entity.getPosterPath(),
                entity.getReleaseDate(),
                entity.getVoteAverage(),
                entity.isAdult()
        );
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