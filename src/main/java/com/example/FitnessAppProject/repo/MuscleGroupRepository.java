package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.MuscleGroup;
import com.example.FitnessAppProject.models.enums.MuscleGroupEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
    Optional<MuscleGroup> findByMuscleGroup(MuscleGroupEnum muscleGroupEnum);
}
