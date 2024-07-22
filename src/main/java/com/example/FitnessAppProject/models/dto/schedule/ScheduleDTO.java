package com.example.FitnessAppProject.models.dto.schedule;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanDTO;

import java.util.List;


public class ScheduleDTO {

    private Long id;
    private Long userId;
    private List<PlanDTO> plans;

    // Getters and setters
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

    public List<PlanDTO> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanDTO> plans) {
        this.plans = plans;
    }
}

