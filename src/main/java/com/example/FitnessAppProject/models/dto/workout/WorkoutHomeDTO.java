package com.example.FitnessAppProject.models.dto.workout;

import java.util.List;

public class WorkoutHomeDTO {
    private List<WorkoutDTO> allWorkouts;

    public WorkoutHomeDTO(List<WorkoutDTO> allWorkouts) {
        this.allWorkouts = allWorkouts;
    }

    public List<WorkoutDTO> getAllWorkouts() {
        return allWorkouts;
    }
}
