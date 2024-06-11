package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
