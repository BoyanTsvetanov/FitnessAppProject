package com.example.FitnessAppProject.models.entity;

import com.example.FitnessAppProject.models.enums.MuscleGroupEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "muscle_groups")
public class MuscleGroup extends BaseEntity{


    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group", nullable = false)
    private MuscleGroupEnum muscleGroup;


    public MuscleGroupEnum getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroupEnum muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
