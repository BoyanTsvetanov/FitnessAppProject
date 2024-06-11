package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM exercises")
    List<Exercise> getAllAvailable();
}
