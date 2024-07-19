package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.TrainingDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingDateRepository extends JpaRepository<TrainingDate, Long> {
}
