package com.example.FitnessAppProject.models.dto.exercise;

import com.example.FitnessAppProject.models.entity.Exercise;
import com.example.FitnessAppProject.models.entity.MuscleGroup;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseDTO {
    private Long id;

    private String description;

    private int repetitions;

    private String mediaUrl;

    private List<String> muscleGroups;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public List<String> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(List<String> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static ExerciseDTO createFromExercise(Exercise exercise){
        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setDescription(exercise.getDescription());
        exerciseDTO.setRepetitions(exercise.getRepetitions());
        exerciseDTO.setMediaUrl(exercise.getMediaUrl());
        exerciseDTO.setMuscleGroups(exercise.getMuscleGroup()  // Ensure to get the list of muscle groups correctly
                .stream()
                .map(MuscleGroup::getMuscleGroup) // Map the MuscleGroup entity to its enum
                .map(Enum::name) // Convert the enum to its string representation
                .collect(Collectors.toList()));

        return exerciseDTO;
    }
    public static ExerciseDTO fromExercise(Exercise exercise){
        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setId(exercise.getId());
        exerciseDTO.setDescription(exercise.getDescription());
        exerciseDTO.setRepetitions(exercise.getRepetitions());
        exerciseDTO.setMediaUrl(exercise.getMediaUrl());

        return exerciseDTO;
    }
}
