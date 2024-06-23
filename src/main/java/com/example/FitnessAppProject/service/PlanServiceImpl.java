package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanHomeDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.entity.Exercise;
import com.example.FitnessAppProject.models.entity.Plan;
import com.example.FitnessAppProject.models.entity.Workout;
import com.example.FitnessAppProject.repo.PlanRepository;
import com.example.FitnessAppProject.repo.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final WorkoutRepository workoutRepository;

    public PlanServiceImpl(PlanRepository planRepository, WorkoutRepository workoutRepository) {
        this.planRepository = planRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    @Transactional
    public void createPlan(PlanDTO planDTO) {
        Plan plan = convertToPlan(planDTO);

        planRepository.save(plan);
    }

    @Override
    @Transactional
    public PlanHomeDTO getAllPlans() {
        List<PlanDTO> allPlans = planRepository.findAll().stream()
                .map(this::convertToPlanDTO)
                .collect(Collectors.toList());

        return new PlanHomeDTO(allPlans);
    }

    @Override
    @Transactional
    public Optional<PlanDTO> getPlanById(Long id) {
        return planRepository.findById(id)
                .map(this::convertToPlanDTO);
    }

    @Override
    @Transactional
    public void updatePlan(Long id, PlanDTO planDTO) {
        Plan existingPlan = planRepository.findById(id).orElseThrow(() -> new NullPointerException("Plan not found"));

        Plan newPlan = convertToPlan(planDTO);

        existingPlan.setName(newPlan.getName());
        existingPlan.setCredits(newPlan.getCredits());
        existingPlan.setWorkouts(newPlan.getWorkouts());

        planRepository.save(existingPlan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    private PlanDTO convertToPlanDTO(Plan plan) {
        PlanDTO dto = new PlanDTO();
        dto.setId(plan.getId());
        dto.setName(plan.getName());
        dto.setCredits(plan.getCredits());
        dto.setWorkouts(plan.getWorkouts().stream()
                .map(this::convertToWorkoutDto)
                .collect(Collectors.toList()));
        return dto;
    }

    private Plan convertToPlan(PlanDTO dto) {
        Plan plan = new Plan();
        plan.setId(dto.getId());
        plan.setName(dto.getName());
        plan.setCredits(dto.getCredits());
        plan.setWorkouts(dto.getWorkouts().stream()
                .map(workoutDto -> workoutRepository.findById(workoutDto.getId()).orElse(null))
                .collect(Collectors.toList()));
        return plan;
    }

    private WorkoutDTO convertToWorkoutDto(Workout workout) {
        WorkoutDTO dto = new WorkoutDTO();
        dto.setId(workout.getId());
        dto.setName(workout.getName());
        dto.setExercises(workout.getExercises().stream()
                .map(this::convertToExerciseDto)
                .collect(Collectors.toList()));
        return dto;
    }

    private ExerciseDTO convertToExerciseDto(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        return dto;
    }
}
