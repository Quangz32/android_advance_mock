package com.example.ojtaadaassignment12.domain.usecase.user;

import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

public class GetUserUseCase {
    private final UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<User> execute(String userId) {
        return userRepository.getUser(userId);
    }
}
