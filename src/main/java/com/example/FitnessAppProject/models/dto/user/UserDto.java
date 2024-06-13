package com.example.FitnessAppProject.models.dto.user;


import com.example.FitnessAppProject.models.dto.role.RoleDTO;
import com.example.FitnessAppProject.models.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<RoleDTO> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    private static Set<Long> processedUserIds = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public static Set<Long> getProcessedUserIds() {
        return processedUserIds;
    }

    public static void setProcessedUserIds(Set<Long> processedUserIds) {
        UserDto.processedUserIds = processedUserIds;
    }

    public static UserDto createFromUser(User user) {
        if (processedUserIds.contains(user.getId())) {
            // Entity already processed, return an empty DTO or handle as needed
            return new UserDto();
        }

        processedUserIds.add(user.getId());

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(RoleDTO.createFromRoles(user.getRoles()));

        processedUserIds.remove(user.getId());  // Remove the processed entity

        return userDto;
    }
}
