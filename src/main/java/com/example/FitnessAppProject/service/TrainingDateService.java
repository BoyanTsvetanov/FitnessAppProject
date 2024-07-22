package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TrainingDateService {
    @Transactional
    void recordTraining(Long userId, Long planId, LocalDate trainingDate);

    List<TrainingDateDTO> getTrainingDatesByUser(Long userId);
}
