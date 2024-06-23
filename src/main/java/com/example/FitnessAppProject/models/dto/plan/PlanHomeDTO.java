package com.example.FitnessAppProject.models.dto.plan;

import java.util.List;

public class PlanHomeDTO {
    private List<PlanDTO> allPlans;

    public PlanHomeDTO(List<PlanDTO> allPlans) {
        this.allPlans = allPlans;
    }

    public List<PlanDTO> getAllPlans() {
        return allPlans;
    }
}
