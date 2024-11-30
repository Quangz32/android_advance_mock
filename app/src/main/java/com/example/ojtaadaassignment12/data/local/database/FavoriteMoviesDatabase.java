package com.example.ojtaadaassignment12.data.local.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ojtaadaassignment12.data.local.dao.MovieDao;
import com.example.ojtaadaassignment12.data.local.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class FavoriteMoviesDatabase extends RoomDatabase {

    private static volatile FavoriteMoviesDatabase INSTANCE;

    public static FavoriteMoviesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteMoviesDatabase.class) {
                if (INSTANCE == null) {
                    Log.d("qzAppDatabase", "Creating new database instance");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FavoriteMoviesDatabase.class, "movies_database")
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
