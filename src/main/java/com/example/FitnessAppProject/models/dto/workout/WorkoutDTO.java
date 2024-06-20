package com.example.FitnessAppProject.models.dto.workout;


import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.entity.Exercise;
import com.example.FitnessAppProject.models.entity.Workout;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkoutDTO {
    private Long id;

    private String name;

    private int runtime;

    private Double credits;

    private Set<Long> exerciseIds;

    private List<ExerciseDTO> exercises;

    // getters and setters


    public Set<Long> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(Set<Long> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    public static WorkoutDTO createFromWorkout(Workout workout) {
        WorkoutDTO workoutDTO = new WorkoutDTO();

        workoutDTO.setId(workout.getId());
        workoutDTO.setName(workout.getName());
        workoutDTO.setRuntime(workout.getRuntime());
        workoutDTO.setCredits(workout.getCredits());

        Set<Long> exerciseIds = workout.getExercises().stream()
                .map(Exercise::getId)
                .collect(Collectors.toSet());

        List<ExerciseDTO> exercises = workout.getExercises().stream()
                .map(ExerciseDTO::createFromExercise)
                .collect(Collectors.toList());

        workoutDTO.setExercises(exercises);

        workoutDTO.setExerciseIds(exerciseIds);


        return workoutDTO;
    }
}
