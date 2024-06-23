package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.plan.PlanDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanHomeDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlanService {

    void createPlan(PlanDTO planDTO);

    @Transactional
    PlanHomeDTO getAllPlans();

    Optional<PlanDTO> getPlanById(Long id);

    @Transactional
    void updatePlan(Long id, PlanDTO planDTO);

    void deletePlan(Long id);
}
