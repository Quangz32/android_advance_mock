package com.example.ojtaadaassignment12.domain.model;

import java.io.Serializable;

public class User implements Serializable {
    private String fullName;
    private String email;
    private String dob;
    private String gender;
    private String avatar;

    public User(String fullName, String email, String dob, String gender, String avatar) {
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

    public String getGender() {
        return gender;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
