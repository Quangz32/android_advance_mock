package com.example.ojtaadaassignment12.domain.usecase.reminder;

import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.repository.ReminderRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class InsertReminderUseCase {
    private final ReminderRepository reminderRepository;

    @Inject
    public InsertReminderUseCase(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }


    public Completable execute(Reminder reminder) {
        return reminderRepository.insertReminder(reminder);
    }
}
