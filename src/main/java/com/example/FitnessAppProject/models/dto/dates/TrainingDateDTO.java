package com.example.FitnessAppProject.models.dto.dates;

import java.time.LocalDateTime;

public class TrainingDateDTO {
    private Long id;
    private LocalDateTime trainingDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDateTime trainingDate) {
        this.trainingDate = trainingDate;
    }
}
