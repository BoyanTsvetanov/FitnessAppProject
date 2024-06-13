package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.entity.Workout;
import com.example.FitnessAppProject.repo.ExerciseRepository;
import com.example.FitnessAppProject.repo.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    // Read
    @Override
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findById(id);
    }

    // Update
    @Override
    public Workout updateWorkout(Long id, Workout workoutDetails) {
        Workout workout = workoutRepository.findById(id).orElseThrow(() -> new NullPointerException("Exercise not found"));

        workout.setName(workoutDetails.getName());
        workout.setRuntime(workoutDetails.getRuntime());
        workout.setExercises(workoutDetails.getExercises());
        workout.setCredits(workoutDetails.getCredits());
        workout.setPlans(workoutDetails.getPlans());

        return workoutRepository.save(workout);
    }

    // Delete
    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.findById(id).ifPresent(workoutRepository::delete);
    }
}
