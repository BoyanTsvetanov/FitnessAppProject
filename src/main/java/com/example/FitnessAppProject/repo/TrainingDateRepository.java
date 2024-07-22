package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import com.example.FitnessAppProject.models.entity.TrainingDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface TrainingDateRepository extends JpaRepository<TrainingDate, Long> {
    List<TrainingDate> findByUserId(Long userId);
}
