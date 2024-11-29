package com.example.ojtaadaassignment12.presenter.ui.setting;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;

import javax.inject.Inject;

public class SettingFragment extends PreferenceFragmentCompat {

    @Inject
    SettingViewModel settingViewModel;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

//        settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        ((App) requireActivity().getApplication()).getAppComponent().inject(this);

        settingViewModel.loadSettings();

        setupPreferenceChangeListeners();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("logd.settingFrag.start", "start");

//         Quan sát LiveData khi Fragment đã có View
        settingViewModel.getCategoryLiveData().observe(getViewLifecycleOwner(), category -> {
            ListPreference categoryPreference = findPreference("category");
            if (categoryPreference != null) {
                categoryPreference.setSummary(category);
            }
        });

        settingViewModel.getMovieRateLiveData().observe(getViewLifecycleOwner(), rate -> {
            SeekBarPreference movieRatePreference = findPreference("movie_rate");
            if (movieRatePreference != null) {
                movieRatePreference.setSummary(String.valueOf(rate));
            }
        });

        settingViewModel.getReleaseYearLiveData().observe(getViewLifecycleOwner(), year -> {
            EditTextPreference releaseYearPreference = findPreference("release_year");
            if (releaseYearPreference != null) {
                releaseYearPreference.setSummary(year);
            }
        });

        settingViewModel.getSortByLiveData().observe(getViewLifecycleOwner(), sortBy -> {
            ListPreference sortByPreference = findPreference("sort_by");
            if (sortByPreference != null) {
                sortByPreference.setSummary(sortBy);
            }
        });
    }

    private void setupPreferenceChangeListeners() {
        ListPreference categoryPreference = findPreference("category");
        SeekBarPreference movieRatePreference = findPreference("movie_rate");
        EditTextPreference releaseYearPreference = findPreference("release_year");
        ListPreference sortByPreference = findPreference("sort_by");

        if (categoryPreference != null) {
            categoryPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                settingViewModel.saveCategory((String) newValue);
                settingViewModel.setCategoryLiveData((String) newValue);
                Log.d("logd.settingFrag.category", (String) newValue);
//                categoryPreference.setSummary((CharSequence) newValue);
                return true;
            });
        }

        if (movieRatePreference != null) {
            movieRatePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                settingViewModel.saveMovieRate((Integer) newValue);
                settingViewModel.setMovieRateLiveData((Integer) newValue);
                Log.d("logd.settingFrag.rate", String.valueOf(newValue));
//                movieRatePreference.setSummary(String.valueOf(newValue));
                return true;
            });
        }

        if (releaseYearPreference != null) {
            releaseYearPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                settingViewModel.saveReleaseYear((String) newValue);
                settingViewModel.setReleaseYearLiveData((String) newValue);
                Log.d("logd.settingFrag.year", (String) newValue);
//                releaseYearPreference.setSummary((CharSequence) newValue);
                return true;
            });
        }

        if (sortByPreference != null) {
            sortByPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                settingViewModel.saveSortBy((String) newValue);
                settingViewModel.setSortByLiveData((String) newValue);
                Log.d("logd.settingFrag.sort", (String) newValue);
//                sortByPreference.setSummary((CharSequence) newValue);
                return true;
            });
        }
    }
}


