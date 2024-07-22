package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.plan.PlanDTO;
import com.example.FitnessAppProject.models.dto.schedule.ScheduleDTO;
import com.example.FitnessAppProject.models.entity.Plan;
import com.example.FitnessAppProject.models.entity.Schedule;
import com.example.FitnessAppProject.repo.PlanRepository;
import com.example.FitnessAppProject.repo.ScheduleRepository;
import com.example.FitnessAppProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public ScheduleDTO getScheduleById(Long id) {
        return scheduleRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    @Transactional
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToEntity(scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    @Override
    @Transactional
    public ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = convertToEntity(scheduleDTO);
        schedule.setId(id);
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ScheduleDTO> findAll() {
        return scheduleRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addPlanToSchedule(Long scheduleId, Long planId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        schedule.getPlans().add(plan);
        scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public void removePlanFromSchedule(Long scheduleId, Long planId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        schedule.getPlans().remove(plan);
        scheduleRepository.save(schedule);
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setUserId(schedule.getUser().getId());
        scheduleDTO.setPlans(schedule.getPlans().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return scheduleDTO;
    }

    private PlanDTO convertToDTO(Plan plan) {
        PlanDTO planDTO = new PlanDTO();
        planDTO.setId(plan.getId());
        // Set other fields as needed
        return planDTO;
    }

    private Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setUser(userRepository.findById(scheduleDTO.getUserId()).orElse(null));
        schedule.setPlans(scheduleDTO.getPlans().stream().map(this::convertToEntity).collect(Collectors.toList()));
        return schedule;
    }

    private Plan convertToEntity(PlanDTO planDTO) {
        Plan plan = new Plan();
        // Set other fields as needed
        return plan;
    }
}
