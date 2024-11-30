package com.example.ojtaadaassignment12.presenter.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.usecase.reminder.DeleteReminderUseCase;
import com.example.ojtaadaassignment12.presenter.ui.reminder.ReminderViewModel;

import java.util.Date;

import javax.inject.Inject;

public class ReminderWorker extends Worker{
    @Inject
    ReminderViewModel reminderViewModel; //Để xoá dữ liệu khỏi DB


    public ReminderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        ((App) context.getApplicationContext()).getAppComponent().inject(this);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Lấy thông tin từ inputData
        int notificationId = getInputData().getInt("id", 0);
        String movieTitle = getInputData().getString("title");
        String movieReleaseDate = getInputData().getString("date");
        float movieRating = getInputData().getFloat("voteAverage", 0f);
        String moviePoster = getInputData().getString("posterPath");
        long timestamp = getInputData().getLong("timestamp", 0);

        Reminder reminder = new Reminder(notificationId, movieTitle, movieReleaseDate, movieRating, moviePoster, timestamp);

        //Gửi thông báo
        showNotification(reminder);

        // Xoá dữ liệu khỏi DB
//        deleteReminderUseCase.execute()
        reminderViewModel.deleteReminder(reminder);

        return Result.success();
    }

    private void showNotification(Reminder reminder) {
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "reminder_channel",
                    "Reminder Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "reminder_channel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Reminder")
                .setContentText(reminder.getMovieTitle() + " will be released on " + reminder.getMovieReleaseDate())
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(reminder.getId(), notification.build());
    }
}

