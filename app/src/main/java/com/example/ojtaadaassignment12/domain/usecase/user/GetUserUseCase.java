package com.example.ojtaadaassignment12.domain.usecase.user;

import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

public class GetUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<User> execute(String userId) {
        return userRepository.getUser(userId);
    }
}
