package com.example.ojtaadaassignment12.data.mapper;

import com.example.ojtaadaassignment12.data.remote.dto.UserDto;
import com.example.ojtaadaassignment12.domain.model.User;

public class UserMapper {
    // Chuyển từ UserDto sang User entity
    public static User toEntity(UserDto userDto) {
        return new User(
                userDto.getFullName(),
                userDto.getEmail(),
                userDto.getDob(),
                userDto.getGender(),
                userDto.getAvatar()
        );
    }

    // Chuyển từ User entity sang UserDto
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getFullName(),
                user.getEmail(),
                user.getDob(),
                user.getGender(),
                user.getAvatar()
        );
    }
}
