package com.example.ojtaadaassignment12.domain.usecase.setting;

import com.example.ojtaadaassignment12.domain.repository.SettingRepository;

import javax.inject.Inject;

public class GetSettingUseCase {
    private final SettingRepository settingRepository;

    @Inject
    public GetSettingUseCase(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public String getCategory() {
        return settingRepository.getCategory();
    }

    public int getMovieRate() {
        return settingRepository.getMovieRate();
    }

    public String getReleaseYear() {
        return settingRepository.getReleaseYear();
    }

    public String getSortBy() {
        return settingRepository.getSortBy();
    }


}
