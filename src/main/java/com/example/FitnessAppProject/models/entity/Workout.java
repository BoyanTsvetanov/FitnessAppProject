package com.example.FitnessAppProject.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int runtime;
    @Column(nullable = false)
    private Double credits;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "workout_id")
    private List<Exercise> exercises;

    @ManyToMany(mappedBy = "workouts")
    private List<Plan> plans;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
