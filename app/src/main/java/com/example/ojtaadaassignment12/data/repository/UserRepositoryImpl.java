package com.example.ojtaadaassignment12.data.repository;

import com.example.ojtaadaassignment12.data.mapper.UserMapper;
import com.example.ojtaadaassignment12.data.remote.dto.UserDto;
import com.example.ojtaadaassignment12.data.remote.firebase.FirebaseUserDataSource;
import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

public class UserRepositoryImpl implements UserRepository {

    private final FirebaseUserDataSource firestoreDataSource;

    public UserRepositoryImpl() {
        firestoreDataSource = new FirebaseUserDataSource();
    }

    @Override
    public CompletableFuture<Void> saveUser(String userId, User user) {
        // Chuyển từ User entity sang UserDto trước khi lưu vào Realtime Database
        UserDto userDto = UserMapper.toDto(user);
        return firestoreDataSource.saveUser(userId, userDto);
    }

    @Override
    public CompletableFuture<User> getUser(String userId) {
        // Lấy UserDto từ Realtime Database và chuyển thành User entity
        return firestoreDataSource.getUser(userId)
                .thenApply(UserMapper::toEntity);
    }
}
