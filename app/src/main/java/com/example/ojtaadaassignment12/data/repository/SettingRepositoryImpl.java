package com.example.ojtaadaassignment12.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.ojtaadaassignment12.domain.repository.SettingRepository;

public class SettingRepositoryImpl implements SettingRepository {
    private final SharedPreferences sharedPreferences;

    public SettingRepositoryImpl(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public String getCategory() {
        return sharedPreferences.getString("category", "Popular Movies");
    }

    @Override
    public int getMovieRate() {
        return sharedPreferences.getInt("movie_rate", 0);
    }

    @Override
    public String getReleaseYear() {
        return sharedPreferences.getString("release_year", "1970");
    }

    @Override
    public String getSortBy() {
        return sharedPreferences.getString("sort_by", "Release Date");
    }

    @Override
    public void saveCategory(String category) {
        sharedPreferences.edit().putString("category", category).apply();
    }

    @Override
    public void saveMovieRate(int movieRate) {
        sharedPreferences.edit().putInt("movie_rate", movieRate).apply();
    }

    @Override
    public void saveReleaseYear(String releaseYear) {
        sharedPreferences.edit().putString("release_year", releaseYear).apply();
    }

    @Override
    public void saveSortBy(String sortBy) {
        sharedPreferences.edit().putString("sort_by", sortBy).apply();
    }
}
