package com.example.ojtaadaassignment12.domain.usecase.reminder;

import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.repository.ReminderRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class DeleteReminderUseCase {
    private final ReminderRepository reminderRepository;

    @Inject
    public DeleteReminderUseCase(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public Completable execute(Reminder reminder) {
        return reminderRepository.deleteReminder(reminder);
    }
}
