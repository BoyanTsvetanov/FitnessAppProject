package com.example.FitnessAppProject.models.dto.plan;

import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.entity.Plan;

import java.util.List;
import java.util.stream.Collectors;

public class PlanDTO {
    private Long id;
    private String name;
    private Double credits;
    private List<WorkoutDTO> workouts;

    private List<Long> workoutIds;

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

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public List<WorkoutDTO> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutDTO> workouts) {
        this.workouts = workouts;
    }

    public List<Long> getWorkoutIds() {
        return workoutIds;
    }

    public void setWorkoutIds(List<Long> workoutIds) {
        this.workoutIds = workoutIds;
    }

    public PlanDTO createFromPlan(Plan plan) {
        PlanDTO dto = new PlanDTO();
        dto.setId(plan.getId());
        dto.setName(plan.getName());
        dto.setCredits(plan.getCredits());

        List<WorkoutDTO> workoutDTOS = plan.getWorkouts().stream()
                .map(WorkoutDTO::createFromWorkout)
                .collect(Collectors.toList());

        dto.setWorkouts(workoutDTOS);

        return dto;
    }


}
