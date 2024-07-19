package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.schedule.ScheduleDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduleService {
    @Transactional
    ScheduleDTO getScheduleById(Long id);

    @Transactional
    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    @Transactional
    ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO);

    void deleteSchedule(Long id);

    @Transactional
    ScheduleDTO addPlanToSchedule(Long scheduleId, Long planId);

    @Transactional
    ScheduleDTO removePlanFromSchedule(Long scheduleId, Long planId);
}
