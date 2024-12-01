package com.example.ojtaadaassignment12.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ojtaadaassignment12.data.local.entity.ReminderEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface ReminderDao {
    @Insert
    void insertReminder(ReminderEntity reminderEntity);

    @Delete
    void deleteReminder(ReminderEntity reminderEntity);

    @Query("SELECT * FROM reminders")
    Observable<List<ReminderEntity>> getAllReminders();

    @Query("SELECT * FROM reminders WHERE id = :reminderId LIMIT 1")
    Observable<ReminderEntity> getReminderById(int reminderId);
}
