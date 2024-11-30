package com.example.ojtaadaassignment12.domain.repository;

import com.example.ojtaadaassignment12.domain.model.User;

import java.util.concurrent.CompletableFuture;

public interface UserRepository {
    CompletableFuture<Void> saveUser(String userId, User user);

    CompletableFuture<User> getUser(String userId);
}

