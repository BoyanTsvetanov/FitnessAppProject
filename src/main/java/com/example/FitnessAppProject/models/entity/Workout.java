package com.example.FitnessAppProject.models.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedEntityGraph(
        name = "Workout.exercises",
        attributeNodes = @NamedAttributeNode("exercises")
)
@Table(name = "workouts")
public class Workout extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int runtime;
    @Column(nullable = false)
    private Double credits;
    @ManyToMany
    @JoinTable(
            name = "workout_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private Set<Exercise> exercises = new HashSet<>();

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

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
