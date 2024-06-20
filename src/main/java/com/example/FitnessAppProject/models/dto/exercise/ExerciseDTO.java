package com.example.FitnessAppProject.models.dto.exercise;

import com.example.FitnessAppProject.models.entity.Exercise;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseDTO {
    private Long id;

    private String name;

    private String description;

    private int repetitions;

    private String mediaUrl;

    private List<String> muscleGroups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        exerciseDTO.setName(exercise.getName());
        exerciseDTO.setDescription(exercise.getDescription());
        exerciseDTO.setRepetitions(exercise.getRepetitions());
        exerciseDTO.setMediaUrl(exercise.getMediaUrl());
        exerciseDTO.setMuscleGroups(exercise.getMuscleGroups());

        return exerciseDTO;
    }
    public static Exercise toEntity(ExerciseDTO exerciseDTO) {
        if (exerciseDTO == null) {
            return null;
        }

        Exercise exercise = new Exercise();
        exercise.setId(exerciseDTO.getId());
        exercise.setName(exerciseDTO.getName());
        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setRepetitions(exerciseDTO.getRepetitions());
        exercise.setMediaUrl(exerciseDTO.getMediaUrl());
        exercise.setMuscleGroups(exerciseDTO.getMuscleGroups());

        return exercise;
    }
//    public static ExerciseDTO fromExercise(Exercise exercise){
//        ExerciseDTO exerciseDTO = new ExerciseDTO();
//
//        exerciseDTO.setId(exercise.getId());
//        exerciseDTO.setDescription(exercise.getDescription());
//        exerciseDTO.setRepetitions(exercise.getRepetitions());
//        exerciseDTO.setMediaUrl(exercise.getMediaUrl());
//        exerciseDTO.setMuscleGroups(
//                exercise.getMuscleGroup().stream()
//                        .map(muscleGroup -> muscleGroup.getMuscleGroup().toString())
//                        .collect(Collectors.toList())
//        );
//        return exerciseDTO;
//    }
}
