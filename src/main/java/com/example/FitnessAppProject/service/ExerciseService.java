package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.dto.exercise.ExerciseHomeDTO;

public interface ExerciseService {
    void createExercise(ExerciseDTO exerciseDTO);

    ExerciseHomeDTO getExerciseData();
    ExerciseDTO getExerciseById(Long exerciseId);

    void deleteExercise(Long id);
    void updateExercise(Long id, ExerciseDTO exerciseDTO);
}
