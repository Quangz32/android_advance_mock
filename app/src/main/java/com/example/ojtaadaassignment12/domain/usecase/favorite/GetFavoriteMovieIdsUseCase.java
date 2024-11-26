package com.example.ojtaadaassignment12.domain.usecase.favorite;

import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetFavoriteMovieIdsUseCase {
    private final FavoriteMovieRepository repository;

    @Inject
    public GetFavoriteMovieIdsUseCase(FavoriteMovieRepository repository) {
        this.repository = repository;
    }

    public Observable<Set<Integer>> execute() {
        return repository.getFavoriteMovieIds().subscribeOn(Schedulers.io()).map(HashSet::new);
    }
}
