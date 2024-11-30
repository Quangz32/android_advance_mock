package com.example.ojtaadaassignment12.domain.repository;

import com.example.ojtaadaassignment12.domain.model.Reminder;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface ReminderRepository {
    Observable<List<Reminder>> getAllReminders();
    Observable<Reminder> getReminderById(int reminderId);
    Completable insertReminder(Reminder reminder);
    Completable deleteReminder(Reminder reminder);
}
