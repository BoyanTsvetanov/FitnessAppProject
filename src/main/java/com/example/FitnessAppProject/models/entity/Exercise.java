package com.example.FitnessAppProject.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity {
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int repetitions;
    @Column(name = "media")
    private String mediaUrl;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "exercise_muscle_groups",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_group_id")
    )
    private List<MuscleGroup> muscleGroups;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public List<MuscleGroup> getMuscleGroup() {
        return muscleGroups;
    }

    public void setMuscleGroup(List<MuscleGroup> muscleGroup) {
        this.muscleGroups = muscleGroup;
    }

}
