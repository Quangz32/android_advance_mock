package com.example.ojtaadaassignment12.domain.usecase.setting;

import com.example.ojtaadaassignment12.domain.repository.SettingRepository;

import javax.inject.Inject;

public class SaveSettingUseCase {
    private final SettingRepository settingRepository;

    @Inject
    public SaveSettingUseCase(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public void saveCategory(String category) {
        settingRepository.saveCategory(category);
    }

    public void saveMovieRate(int rate) {
        settingRepository.saveMovieRate(rate);
    }

    public void saveReleaseYear(String year) {
        settingRepository.saveReleaseYear(year);
    }

    public void saveSortBy(String sortBy) {
        settingRepository.saveSortBy(sortBy);
    }
}
