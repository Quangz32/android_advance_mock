package com.example.ojtaadaassignment12.domain.usecase.reminder;

import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.repository.ReminderRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetAllReminderUseCase {
    private final ReminderRepository reminderRepository;

    @Inject
    public GetAllReminderUseCase(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public Observable<List<Reminder>> execute() {
        return reminderRepository.getAllReminders();
    }
}
