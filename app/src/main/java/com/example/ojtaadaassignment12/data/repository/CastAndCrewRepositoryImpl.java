package com.example.ojtaadaassignment12.data.repository;

import com.example.ojtaadaassignment12.data.mapper.CastAndCrewMapper;
import com.example.ojtaadaassignment12.data.remote.api.CastAndCrewApi;
import com.example.ojtaadaassignment12.domain.model.CastAndCrew;
import com.example.ojtaadaassignment12.domain.repository.CastAndCrewRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class CastAndCrewRepositoryImpl implements CastAndCrewRepository {
    CastAndCrewApi castAndCrewApi;
    CastAndCrewMapper castAndCrewMapper;

    @Inject
    public CastAndCrewRepositoryImpl(CastAndCrewApi castAndCrewApi, CastAndCrewMapper castAndCrewMapper) {
        this.castAndCrewApi = castAndCrewApi;
        this.castAndCrewMapper = castAndCrewMapper;
    }

//    private final MovieApi movieApi;    //Dùng để load Movie từ API
//    private final MovieMapper movieMapper;  //Dùng để map Movie từ DTO sang Domain Model
////    private final MovieDao movieDao;    //Dùng để tìm Movie yêu thích

//    @Inject
//    public MovieRepositoryImpl(MovieApi movieApi, MovieMapper movieMapper) {
//        this.movieApi = movieApi;
//        this.movieMapper = movieMapper;
//    }

//    @Override
//    public Single<List<Movie>> getMovies(String category, String apiKey, int page) {
//        return movieApi.getMovies(category, apiKey, page)
//                .map(response -> {
//                    List<MovieDto> movieDtos = response.getResults();
//                    return movieMapper.mapDtoListToDomainList(movieDtos);
//                });
//    }

    @Override
    public Single<List<CastAndCrew>> getCastAndCrews(int movieId, String apiKey) {
        return castAndCrewApi.getCastAndCrews(movieId, apiKey)
                .map(response -> castAndCrewMapper.mapDtoListToDomainList(response.getCastAndCrewDtoList()));
//        return  null;
    }
}
