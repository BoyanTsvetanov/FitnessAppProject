package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutHomeDTO;
import com.example.FitnessAppProject.models.entity.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    void createWorkout(WorkoutDTO workout);

    // Read
    WorkoutHomeDTO getAllWorkouts();

    Optional<Workout> getWorkoutById(Long id);

    // Update
    void updateWorkout(Long id, WorkoutDTO workoutDetails);

    // Delete
    void deleteWorkout(Long id);
}
