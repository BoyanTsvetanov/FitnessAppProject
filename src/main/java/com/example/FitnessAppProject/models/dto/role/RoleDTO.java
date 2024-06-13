package com.example.FitnessAppProject.models.dto.role;

import com.example.FitnessAppProject.models.entity.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RoleDTO {
    private Long id;
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static List<RoleDTO> createFromRoles(List<RoleEntity> roles) {
        return roles.stream()
                .map(role -> {
                    RoleDTO roleDto = new RoleDTO();
                    roleDto.setId(role.getId());
                    roleDto.setRoleName(String.valueOf(role.getRole()));
                    return roleDto;
                })
                .collect(Collectors.toList());
    }
}
