package com.example.FitnessAppProject.models.dto.schedule;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanDTO;

import java.util.List;


public class ScheduleDTO {
    private Long id;
    private Long userId;
    private List<TrainingDateDTO> trainingDates;
    private List<PlanDTO> plans;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TrainingDateDTO> getTrainingDates() {
        return trainingDates;
    }

    public void setTrainingDates(List<TrainingDateDTO> trainingDates) {
        this.trainingDates = trainingDates;
    }

    public List<PlanDTO> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanDTO> plans) {
        this.plans = plans;
    }
}

