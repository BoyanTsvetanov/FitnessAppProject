package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutHomeDTO;
import com.example.FitnessAppProject.models.entity.Exercise;
import com.example.FitnessAppProject.models.entity.Workout;
import com.example.FitnessAppProject.repo.ExerciseRepository;
import com.example.FitnessAppProject.repo.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @Transactional
    public void createWorkout(WorkoutDTO workoutDTO) {
        Workout workout = new Workout();
        workout.setName(workoutDTO.getName());
        workout.setRuntime(workoutDTO.getRuntime());
        workout.setCredits(workoutDTO.getCredits());

        Set<Exercise> exercises = workoutDTO.getExerciseIds().stream()
                .map(id -> {
                    Exercise exercise = new Exercise();
                    exercise.setId(id);
                    return exercise;
                })
                .collect(Collectors.toSet());
        workout.setExercises(exercises);

        workoutRepository.save(workout);
    }


    @Override
    @Transactional
    public WorkoutHomeDTO getAllWorkouts() {
        List<WorkoutDTO> allWorkouts = workoutRepository.findAll().stream()
                .map(WorkoutDTO::createFromWorkout)
                .collect(Collectors.toList());

        return new WorkoutHomeDTO(allWorkouts);
    }

    @Override
    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findById(id);
    }

    // Update
    @Override
    @Transactional
    public void updateWorkout(Long id, WorkoutDTO workoutDetails) {
        Workout workout = workoutRepository.findById(id).orElseThrow(() -> new NullPointerException("Workout not found"));
        workout.setName(workoutDetails.getName());
        workout.setRuntime(workoutDetails.getRuntime());
        workout.setCredits(workoutDetails.getCredits());

        Set<Exercise> exercises = workoutDetails.getExerciseIds().stream()
                .map(exerciseId -> {
                    Exercise exercise = new Exercise();
                    exercise.setId(exerciseId);
                    return exercise;
                })
                .collect(Collectors.toSet());
        workout.setExercises(exercises);

        workoutRepository.save(workout);
    }

    // Delete
    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.findById(id).ifPresent(workoutRepository::delete);
    }
}
