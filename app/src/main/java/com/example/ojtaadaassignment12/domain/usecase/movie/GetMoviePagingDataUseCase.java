package com.example.ojtaadaassignment12.domain.usecase.movie;

import androidx.paging.PagingData;

import com.example.ojtaadaassignment12.data.repository.MovieRepositoryPaging;
import com.example.ojtaadaassignment12.domain.model.Movie;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class GetMoviePagingDataUseCase {
    private final MovieRepositoryPaging movieRepositoryPaging;

    @Inject
    public GetMoviePagingDataUseCase(MovieRepositoryPaging movieRepositoryPaging) {
        this.movieRepositoryPaging = movieRepositoryPaging;
    }

    public Flowable<PagingData<Movie>> get(CoroutineScope coroutineScope) {
        return movieRepositoryPaging.getMovies(coroutineScope);
    }

}
