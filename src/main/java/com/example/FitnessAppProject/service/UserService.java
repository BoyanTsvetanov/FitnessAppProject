package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.user.UserRegisterBindingDto;
import com.example.FitnessAppProject.models.entity.User;

public interface UserService {
    boolean register(UserRegisterBindingDto userRegisterBindingDto) throws Exception;
    User getCurrentUser();
}
