package com.example.FitnessAppProject.models.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "plans")
public class Plan extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double credits;
    @ManyToMany
    @JoinTable(
            name = "plan_workouts",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id")
    )
    private List<Workout> workouts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }


}
