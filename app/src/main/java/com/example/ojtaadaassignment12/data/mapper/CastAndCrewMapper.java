package com.example.ojtaadaassignment12.data.mapper;

import com.example.ojtaadaassignment12.data.remote.dto.CastAndCrewDto;
import com.example.ojtaadaassignment12.domain.model.CastAndCrew;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class CastAndCrewMapper {
    public CastAndCrew mapToDomain(CastAndCrewDto dto) {
        return new CastAndCrew(
                dto.getId(),
                dto.getBackdropPath(),
                dto.getName()
        );
    }

    public List<CastAndCrew> mapDtoListToDomainList(List<CastAndCrewDto> dtos) {
        List<CastAndCrew> castAndCrews = new ArrayList<>();
        for (CastAndCrewDto dto : dtos) {
            castAndCrews.add(mapToDomain(dto));
        }
        return castAndCrews;
    }
}
