package com.example.ojtaadaassignment12.data.local.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ojtaadaassignment12.data.local.dao.ReminderDao;
import com.example.ojtaadaassignment12.data.local.entity.ReminderEntity;

@Database(entities = {ReminderEntity.class}, version = 1, exportSchema = false)
public abstract class RemindersDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "reminders_database";
    private static volatile RemindersDatabase INSTANCE;

    public static RemindersDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteMoviesDatabase.class) {
                if (INSTANCE == null) {
                    Log.d("qzAppDatabase", "Creating new database instance");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RemindersDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration() // Cho phép phá hủy dữ liệu cũ
                            .build();
                }
            }
        } else {
            Log.d("qzAppDatabase", "Returning existing database instance");
        }
        return INSTANCE;
    }

    public abstract ReminderDao reminderDao();
}
