package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.dto.exercise.ExerciseHomeDTO;
import com.example.FitnessAppProject.models.entity.Exercise;
import com.example.FitnessAppProject.models.entity.MuscleGroup;
import com.example.FitnessAppProject.models.enums.MuscleGroupEnum;
import com.example.FitnessAppProject.repo.ExerciseRepository;
import com.example.FitnessAppProject.repo.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final MuscleGroupRepository muscleGroupRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, MuscleGroupRepository muscleGroupRepository) {
        this.exerciseRepository = exerciseRepository;
        this.muscleGroupRepository = muscleGroupRepository;
    }

    @Override
    public void createExercise(ExerciseDTO exerciseDTO) {

        Exercise exercise = new Exercise();

        exercise.setDescription(exerciseDTO.getDescription());
        exercise.setRepetitions(exerciseDTO.getRepetitions());
        exercise.setMediaUrl(exerciseDTO.getMediaUrl());
        List<MuscleGroup> muscleGroupEntities = exerciseDTO.getMuscleGroups().stream()
                .map(muscleGroupName -> {
                    MuscleGroup muscleGroup = muscleGroupRepository.findByMuscleGroup(MuscleGroupEnum.valueOf(muscleGroupName))
                            .orElseThrow(() -> new IllegalArgumentException("Muscle group not found: " + muscleGroupName));
                    return muscleGroup;
                })
                .collect(Collectors.toList());

        exercise.setMuscleGroup(muscleGroupEntities);

        exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseHomeDTO getExerciseData(){
        List<ExerciseDTO> allExercises = exerciseRepository.getAllAvailable().stream()
                .map(ExerciseDTO::fromExercise)
                .collect(Collectors.toList());

        return new ExerciseHomeDTO(allExercises);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public void updateExercise(Long id, ExerciseDTO exerciseDTO) {
        Exercise existingExercise = exerciseRepository.findById(id).orElseThrow(() -> new NullPointerException("Exercise not found"));

        existingExercise.setDescription(exerciseDTO.getDescription());
        existingExercise.setRepetitions(exerciseDTO.getRepetitions());
        existingExercise.setMediaUrl(exerciseDTO.getMediaUrl());
        List<MuscleGroup> muscleGroupEntities = exerciseDTO.getMuscleGroups().stream()
                .map(muscleGroupName -> {
                    MuscleGroup muscleGroup = new MuscleGroup();
                    muscleGroup.setMuscleGroup(MuscleGroupEnum.valueOf(muscleGroupName));
                    return muscleGroup;
                })
                .collect(Collectors.toList());

        existingExercise.setMuscleGroup(muscleGroupEntities);

        exerciseRepository.save(existingExercise);
    }
}
