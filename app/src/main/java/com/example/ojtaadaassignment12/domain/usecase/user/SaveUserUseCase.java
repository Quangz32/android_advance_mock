package com.example.ojtaadaassignment12.domain.usecase.user;

import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

public class SaveUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public SaveUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<Void> execute(String userId, User user) {
        return userRepository.saveUser(userId, user);
    }
}
