package com.example.ojtaadaassignment12.domain.usecase.reminder;
import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.repository.ReminderRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
public class GetReminderByIdUseCase {
    private final ReminderRepository reminderRepository;

    @Inject
    public GetReminderByIdUseCase(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }


    public Observable<Reminder> execute(int reminderId) {
        return reminderRepository.getReminderById(reminderId);
    }
}
