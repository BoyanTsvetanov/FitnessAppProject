package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.entity.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    Workout createWorkout(Workout workout);

    // Read
    List<Workout> getAllWorkouts();

    Optional<Workout> getWorkoutById(Long id);

    // Update
    Workout updateWorkout(Long id, Workout workoutDetails);

    // Delete
    void deleteWorkout(Long id);
}
