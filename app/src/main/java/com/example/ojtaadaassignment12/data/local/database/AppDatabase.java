package com.example.ojtaadaassignment12.data.local.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ojtaadaassignment12.data.local.dao.MovieDao;
import com.example.ojtaadaassignment12.data.local.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    Log.d("qzAppDatabase", "Creating new database instance");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "movies_database")
                            .fallbackToDestructiveMigration() // Cho phép phá hủy dữ liệu cũ
                            .build();
                }
            }
        } else {
            Log.d("qzAppDatabase", "Returning existing database instance");
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();
}
