package com.example.ojtaadaassignment12.presenter.ui.user;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.domain.usecase.user.GetUserUseCase;
import com.example.ojtaadaassignment12.domain.usecase.user.SaveUserUseCase;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserProfileViewModel extends ViewModel {

    private final GetUserUseCase getUserUseCase;
    private final SaveUserUseCase saveUserUseCase;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    @Inject
    public UserProfileViewModel(GetUserUseCase getUserUseCase,
                                SaveUserUseCase saveUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.saveUserUseCase = saveUserUseCase;
    }

    public LiveData<User> getUser(String userId) {
        CompletableFuture<User> future = getUserUseCase.execute(userId);
        future.whenComplete((user, throwable) -> {
            if (throwable != null) {
                Log.e("UserViewModel", "Error fetching user", throwable);
            } else {
                userLiveData.setValue(user);
            }
        });
        return userLiveData;
    }

    public void saveUser(String userId, User user) {
        CompletableFuture<Void> future = saveUserUseCase.execute(userId, user);
        future.whenComplete((aVoid, throwable) -> {
            if (throwable != null) {
                Log.e("UserViewModel", "Error saving user", throwable);
            } else {
                Log.d("UserViewModel", "User saved successfully");
            }
        });
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
