package com.example.FitnessAppProject.repo;

import com.example.FitnessAppProject.models.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
