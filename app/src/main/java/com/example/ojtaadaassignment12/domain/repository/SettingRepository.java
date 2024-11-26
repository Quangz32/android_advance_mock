package com.example.ojtaadaassignment12.domain.repository;

public interface SettingRepository {
    String getCategory();

    int getMovieRate();

    String getReleaseYear();

    String getSortBy();

    void saveCategory(String category);

    void saveMovieRate(int movieRate);

    void saveReleaseYear(String releaseYear);

    void saveSortBy(String sortBy);
}
