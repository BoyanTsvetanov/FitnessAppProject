package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.RoleEntity;
import com.example.FitnessAppProject.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(UserRoleEnum defaultRole);
}
