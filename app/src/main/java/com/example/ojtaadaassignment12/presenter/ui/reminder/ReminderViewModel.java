package com.example.ojtaadaassignment12.presenter.ui.reminder;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.usecase.reminder.DeleteReminderUseCase;
import com.example.ojtaadaassignment12.domain.usecase.reminder.GetAllReminderUseCase;
import com.example.ojtaadaassignment12.domain.usecase.reminder.GetReminderByIdUseCase;
import com.example.ojtaadaassignment12.domain.usecase.reminder.InsertReminderUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@Singleton
public class ReminderViewModel extends ViewModel {
    GetAllReminderUseCase getAllReminderUseCase;
    GetReminderByIdUseCase getReminderByIdUseCase;
    InsertReminderUseCase insertReminderUseCase;
    DeleteReminderUseCase deleteReminderUseCase;

    MutableLiveData<List<Reminder>> reminders = new MutableLiveData<>();

    CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public ReminderViewModel(GetAllReminderUseCase getAllReminderUseCase,
                             GetReminderByIdUseCase getReminderByIdUseCase,
                             InsertReminderUseCase insertReminderUseCase,
                             DeleteReminderUseCase deleteReminderUseCase) {
        this.getAllReminderUseCase = getAllReminderUseCase;
        this.getReminderByIdUseCase = getReminderByIdUseCase;
        this.insertReminderUseCase = insertReminderUseCase;
        this.deleteReminderUseCase = deleteReminderUseCase;
    }

    public MutableLiveData<List<Reminder>> getRemindersLiveData(){
        return reminders;
    }

    public void fetchReminders() {
        disposables.add(
                getAllReminderUseCase.execute()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                reminders::postValue,
                                Throwable::printStackTrace
                        )
        );
    }

    public void insertReminder(Reminder reminder) {
        disposables.add(
                insertReminderUseCase.execute(reminder)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::fetchReminders,
                                Throwable::printStackTrace
                        )
        );
    }

    public void deleteReminder(Reminder reminder) {
        disposables.add(
                deleteReminderUseCase.execute(reminder)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::fetchReminders,
                                Throwable::printStackTrace
                        )
        );
    }



    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}
