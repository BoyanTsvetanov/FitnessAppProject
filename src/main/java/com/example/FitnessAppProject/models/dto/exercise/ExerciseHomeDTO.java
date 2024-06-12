package com.example.FitnessAppProject.models.dto.exercise;

import java.util.List;
import java.util.Set;

public class ExerciseHomeDTO {
    private List<ExerciseDTO> allExercises;

    public ExerciseHomeDTO(List<ExerciseDTO> allExercises) {
        this.allExercises = allExercises;
    }

    public List<ExerciseDTO> getAllExercises() {
        return allExercises;
    }
}
