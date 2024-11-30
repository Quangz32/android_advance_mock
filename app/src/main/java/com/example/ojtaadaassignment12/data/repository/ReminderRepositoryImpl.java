package com.example.ojtaadaassignment12.data.repository;

import com.example.ojtaadaassignment12.data.local.dao.ReminderDao;
import com.example.ojtaadaassignment12.data.mapper.ReminderMapper;
import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.repository.ReminderRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReminderRepositoryImpl implements ReminderRepository {
    private final ReminderDao reminderDao;

    @Inject
    public ReminderRepositoryImpl(ReminderDao reminderDao) {
        this.reminderDao = reminderDao;
    }


    @Override
    public Observable<List<Reminder>> getAllReminders() {
        return reminderDao.getAllReminders().subscribeOn(Schedulers.io())
                .map(ReminderMapper::mapToDomainList);
    }

    @Override
    public Observable<Reminder> getReminderById(int reminderId) {
        return reminderDao.getReminderById(reminderId).subscribeOn(Schedulers.io())
                .map(ReminderMapper::mapToDomain);
    }
//        return Completable.fromAction(() -> movieDao.insertMovie(movieMapper.mapToEntity(movie)))
//            .subscribeOn(Schedulers.io());  // Thực thi trên background thread
    @Override
    public Completable insertReminder(Reminder reminder) {
        return Completable.fromAction(()-> reminderDao.insertReminder(ReminderMapper.mapToEntity(reminder)))
                        .subscribeOn(Schedulers.io());
//        reminderDao.insertReminder(ReminderMapper.mapToEntity(reminder));
    }

    @Override
    public Completable deleteReminder(Reminder reminder) {
        return Completable.fromAction(()-> reminderDao.deleteReminder(ReminderMapper.mapToEntity(reminder)))
                .subscribeOn(Schedulers.io());
//        reminderDao.deleteReminder(ReminderMapper.mapToEntity(reminder));
    }
}
