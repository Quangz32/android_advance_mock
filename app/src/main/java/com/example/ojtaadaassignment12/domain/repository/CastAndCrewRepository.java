package com.example.ojtaadaassignment12.domain.repository;

import com.example.ojtaadaassignment12.domain.model.CastAndCrew;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CastAndCrewRepository {
    Single<List<CastAndCrew>> getCastAndCrews(int movieId, String apiKey);

}
