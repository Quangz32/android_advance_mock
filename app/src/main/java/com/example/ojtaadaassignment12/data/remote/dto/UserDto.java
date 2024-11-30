package com.example.ojtaadaassignment12.data.remote.dto;

public class UserDto {
    private String fullName;
    private String email;
    private String dob;
    private String gender;
    private String avatar;

    public UserDto() {
        // Firebase cần constructor này để tạo đối tượng
    }

    public UserDto(String fullName, String email, String dob, String gender, String avatar) {
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.avatar = avatar;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
