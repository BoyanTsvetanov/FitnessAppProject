package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.schedule.ScheduleDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScheduleService {
    @Transactional
    ScheduleDTO getScheduleById(Long id);

    @Transactional
    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    @Transactional
    ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO);

    void deleteSchedule(Long id);

    @Transactional
    void addPlanToSchedule(Long scheduleId, Long planId);

    @Transactional
    void removePlanFromSchedule(Long scheduleId, Long planId);

    List<ScheduleDTO> findAll();
}
