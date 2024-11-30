package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.databinding.FragmentMovieDetailBinding;
import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.presenter.binding.BindingAdapters;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesViewModel;
import com.example.ojtaadaassignment12.presenter.ui.reminder.ReminderViewModel;
import com.example.ojtaadaassignment12.presenter.utils.ReminderWorker;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class MovieDetailFragment extends Fragment {
    FragmentMovieDetailBinding binding;

    @Inject
    MovieDetailViewModel viewModel;

    @Inject
    FavoriteMoviesViewModel favoriteMoviesViewModel;

    @Inject
    ReminderViewModel reminderViewModel;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ((App) requireActivity().getApplication()).getAppComponent().inject(this);

//        viewModel = new ViewModelProvider(requireActivity()).get(MovieDetailViewModel.class); //?? Singleton
//        favoriteMoviesViewModel = new ViewModelProvider(requireActivity()).get(FavoriteMoviesViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
//        binding.setMovie(new Movie(10, "Hello", "Long overview", "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg", "2020-11-11", 5.5f, false, true));
        binding.setLifecycleOwner(this);

        binding.imgStar.setOnClickListener(v -> {
            favoriteMoviesViewModel.toggleFavorite(binding.getMovie());

            binding.getMovie().setFavorite(!binding.getMovie().isFavorite());
            BindingAdapters.setCustomStarTint(binding.imgStar, binding.getMovie().isFavorite());
//            Movie movie = viewModel.getMovieLiveData().getValue();
//            viewModel.toggleFavorite();
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getMovieLiveData().observe(getViewLifecycleOwner(), movie -> {
            binding.setMovie(movie);
            viewModel.fetchCastAndCrew(movie.getId(), "e7631ffcb8e766993e5ec0c1f4245f93");
        });

        viewModel.getCastAndCrewLiveData().observe(getViewLifecycleOwner(), castAndCrews -> {
//            binding.setCastAndCrews(castAndCrews);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            binding.recyclerView.setLayoutManager(linearLayoutManager);
            binding.recyclerView.setAdapter(new CastAndCrewAdapter(castAndCrews));
            Log.d("log.cast.crew", castAndCrews.toString());
        });

//        viewModel.fetchCastAndCrew();

        binding.reminderButton.setOnClickListener(v -> openDateTimePicker());


    }

    private void openDateTimePicker() {
        Calendar calendar = Calendar.getInstance();

        // DatePickerDialog với Spinner style
        DatePickerDialog datePicker = new DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // Sau khi chọn ngày, hiển thị TimePickerDialog với Spinner style
                    TimePickerDialog timePicker = new TimePickerDialog(
                            requireContext(),
                            android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                            (timeView, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                long reminderTime = calendar.getTimeInMillis();
                                Toast.makeText(requireContext(),
                                        "time: "+ reminderTime, Toast.LENGTH_SHORT).show();

                                Movie movie = Objects.requireNonNull(viewModel.getMovieLiveData().getValue()).getClone();
                                Reminder reminder = new Reminder((int) reminderTime, movie.getTitle(), movie.getReleaseDate(), movie.getVoteAverage(), movie.getPosterPath(), reminderTime );

                                //Thêm vào DB
                                reminderViewModel.insertReminder(reminder);

                                scheduleReminder(reminder);
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                    );
                    timePicker.show();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePicker.show();
    }

    private void scheduleReminder(Reminder reminder) {
        new Thread(() -> {
            Data inputData = new Data.Builder()
                    .putInt("id", reminder.getId())
                    .putString("title", reminder.getMovieTitle())
                    .putString("date", reminder.getMovieReleaseDate())
                    .putFloat("voteAverage", reminder.getMovieRating())
                    .putString("posterPath", reminder.getMoviePoster())
                    .putLong("timestamp", reminder.getTimestamp())
                    .build();

            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ReminderWorker.class)
                    .setInitialDelay(reminder.getTimestamp() - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .setInputData(inputData)
                    .build();

            WorkManager.getInstance(requireContext()).enqueue(workRequest);
        }).start();
    }

}