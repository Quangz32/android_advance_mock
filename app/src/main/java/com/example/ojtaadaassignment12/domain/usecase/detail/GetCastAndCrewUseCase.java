package com.example.ojtaadaassignment12.domain.usecase.detail;

import com.example.ojtaadaassignment12.domain.model.CastAndCrew;
import com.example.ojtaadaassignment12.domain.repository.CastAndCrewRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetCastAndCrewUseCase {
    private final CastAndCrewRepository castAndCrewRepository;

    @Inject
    public GetCastAndCrewUseCase(CastAndCrewRepository castAndCrewRepository) {
        this.castAndCrewRepository = castAndCrewRepository;
    }

    public Single<List<CastAndCrew>> execute(int movieId, String apiKey) {
        return castAndCrewRepository.getCastAndCrews(movieId, apiKey);
    }

}
