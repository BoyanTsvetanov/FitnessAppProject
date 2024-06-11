package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
