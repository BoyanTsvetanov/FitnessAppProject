package com.example.FitnessAppProject.models.dto.user;

import com.example.FitnessAppProject.models.enums.Gender;
import jakarta.validation.constraints.*;


public class UserRegisterBindingDto {
    @Size(min = 3, max = 25, message = "Username length must be between 3 and 20 characters!")
    @NotBlank(message = "Can't be empty!")
    private String username;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotBlank(message = "Can't be empty!")
    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotBlank
    private Gender gender;

    @Positive
    private int age;

    @NotBlank
    private Double weight;

    @NotBlank
    private Double height;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
