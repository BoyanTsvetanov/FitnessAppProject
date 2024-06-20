package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.entity.Workout;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @EntityGraph(value = "Workout.exercises")
    Optional<Workout> findById(Long id);

    @EntityGraph(value = "Workout.exercises")
    List<Workout> findAll();
}
