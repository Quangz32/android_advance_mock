package com.example.ojtaadaassignment12.domain.model;

public class CastAndCrew {
    int id;
    String backdropPath;
    String name;

    public CastAndCrew(int id, String backdropPath, String name) {
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
        return "CastAndCrew{" +
                "id=" + id +
                ", backdropPath='" + backdropPath + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
