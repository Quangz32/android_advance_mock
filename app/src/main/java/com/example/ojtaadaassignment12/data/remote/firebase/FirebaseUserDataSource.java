package com.example.ojtaadaassignment12.data.remote.firebase;

import com.example.ojtaadaassignment12.data.remote.dto.UserDto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

public class FirebaseUserDataSource {

    private final DatabaseReference databaseReference;

    @Inject
    public FirebaseUserDataSource(DatabaseReference databaseReference) {
//        // Truy cập vào Realtime Database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference("users");
        this.databaseReference = databaseReference;
    }

    // Lưu người dùng vào Realtime Database (sử dụng UserDto)
    public CompletableFuture<Void> saveUser(String userId, UserDto userDto) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        databaseReference.child(userId)
                .setValue(userDto)  // Lưu UserDto vào Realtime Database
                .addOnSuccessListener(aVoid -> future.complete(null))
                .addOnFailureListener(future::completeExceptionally);
        return future;
    }

    // Lấy thông tin người dùng từ Realtime Database (trả về UserDto)
    public CompletableFuture<UserDto> getUser(String userId) {
        CompletableFuture<UserDto> future = new CompletableFuture<>();
        databaseReference.child(userId)
                .get()
                .addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        UserDto userDto = snapshot.getValue(UserDto.class);
                        future.complete(userDto);
                    } else {
                        future.completeExceptionally(new Exception("User not found"));
                    }
                })
                .addOnFailureListener(future::completeExceptionally);
        return future;
    }
}
