package com.example.ojtaadaassignment12.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class CastAndCrewDto {
    @SerializedName("id")
    int id;

    @SerializedName("profile_path")
    String backdropPath;

    @SerializedName("name")
    String name;

    public CastAndCrewDto(int id, String backdropPath, String name) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CastAndCrewDto{" +
                "id=" + id +
//                ", backdropPath='" + backdropPath + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
