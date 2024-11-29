package com.example.ojtaadaassignment12.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastAndCrewResponse {
    @SerializedName("cast")
    List<CastAndCrewDto> castList;

    @SerializedName("crew")
    List<CastAndCrewDto> crewList;

    public List<CastAndCrewDto> getCastAndCrewDtoList() {
        List<CastAndCrewDto> castAndCrewDtoList = castList;
        castAndCrewDtoList.addAll(crewList);
        return castAndCrewDtoList;
    }

}
