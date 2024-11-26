package com.example.ojtaadaassignment12.presenter.ui.setting;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ojtaadaassignment12.R;

public class SettingFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Lấy các Preference cần cập nhật summary
        ListPreference categoryPreference = findPreference("category");
        Preference movieRatePreference = findPreference("movie_rate");
        Preference releaseYearPreference = findPreference("release_year");
        ListPreference sortByPreference = findPreference("sort_by");

        // Thiết lập summary cho các Preference
        PreferenceManager.getDefaultSharedPreferences(requireContext()).getAll();
        if (categoryPreference == null || movieRatePreference == null ||
                releaseYearPreference == null || sortByPreference == null){
            Log.d("logd.Setting", "nulllll");
            return;
        }

        categoryPreference.setSummary(categoryPreference.getEntries()[categoryPreference.findIndexOfValue(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("category", "Popular Movies"))]);
        movieRatePreference.setSummary(String.valueOf(PreferenceManager.getDefaultSharedPreferences(requireContext()).getInt("movie_rate", 0)));
        releaseYearPreference.setSummary(String.valueOf(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("release_year", "")));
        sortByPreference.setSummary(sortByPreference.getEntries()[sortByPreference.findIndexOfValue(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("sort_by", "Release Date"))]);

        // Thiết lập listener cho mỗi Preference để cập nhật summary
        categoryPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            categoryPreference.setSummary(categoryPreference.getEntries()[categoryPreference.findIndexOfValue((String) newValue)]);
            return true;
        });

        movieRatePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            movieRatePreference.setSummary(String.valueOf(newValue));
            return true;
        });

        releaseYearPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            releaseYearPreference.setSummary(String.valueOf(newValue));
            return true;
        });

        sortByPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            sortByPreference.setSummary(sortByPreference.getEntries()[sortByPreference.findIndexOfValue((String) newValue)]);
            return true;
        });


    }
}