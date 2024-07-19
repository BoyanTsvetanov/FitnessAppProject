package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanDTO;
import com.example.FitnessAppProject.models.dto.schedule.ScheduleDTO;
import com.example.FitnessAppProject.models.entity.Plan;
import com.example.FitnessAppProject.models.entity.Schedule;
import com.example.FitnessAppProject.models.entity.TrainingDate;
import com.example.FitnessAppProject.repo.PlanRepository;
import com.example.FitnessAppProject.repo.ScheduleRepository;
import com.example.FitnessAppProject.repo.TrainingDateRepository;
import com.example.FitnessAppProject.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TrainingDateRepository trainingDateRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               TrainingDateRepository trainingDateRepository,
                               PlanRepository planRepository,
                               UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.trainingDateRepository = trainingDateRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ScheduleDTO getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found for id: " + id));
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
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found for id: " + id));
        updateEntityFromDTO(schedule, scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule not found for id: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ScheduleDTO addPlanToSchedule(Long scheduleId, Long planId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found for id: " + scheduleId));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found for id: " + planId));
        schedule.getPlans().add(plan);
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    @Override
    @Transactional
    public ScheduleDTO removePlanFromSchedule(Long scheduleId, Long planId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found for id: " + scheduleId));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found for id: " + planId));
        schedule.getPlans().remove(plan);
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setUserId(schedule.getUser().getId());
        scheduleDTO.setTrainingDates(schedule.getTrainingDates().stream().map(this::convertToDTO).collect(Collectors.toList()));
        scheduleDTO.setPlans(schedule.getPlans().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return scheduleDTO;
    }

    private TrainingDateDTO convertToDTO(TrainingDate trainingDate) {
        TrainingDateDTO trainingDateDTO = new TrainingDateDTO();
        trainingDateDTO.setId(trainingDate.getId());
        trainingDateDTO.setTrainingDate(trainingDate.getTrainingDate());
        return trainingDateDTO;
    }

    private PlanDTO convertToDTO(Plan plan) {
        PlanDTO planDTO = new PlanDTO();
        planDTO.setId(plan.getId());
        // Set other fields as needed
        return planDTO;
    }

    private Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setUser(userRepository.findById(scheduleDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + scheduleDTO.getUserId())));
        schedule.setTrainingDates(scheduleDTO.getTrainingDates().stream()
                .map(this::convertToEntity)
                .peek(trainingDate -> trainingDate.setSchedule(schedule))
                .collect(Collectors.toList()));
        schedule.setPlans(scheduleDTO.getPlans().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
        return schedule;
    }

    private TrainingDate convertToEntity(TrainingDateDTO trainingDateDTO) {
        TrainingDate trainingDate = new TrainingDate();
        trainingDate.setTrainingDate(trainingDateDTO.getTrainingDate());
        return trainingDate;
    }

    private Plan convertToEntity(PlanDTO planDTO) {
        Plan plan = new Plan();
        // Set other fields as needed
        return plan;
    }

    private void updateEntityFromDTO(Schedule schedule, ScheduleDTO scheduleDTO) {
        schedule.setUser(userRepository.findById(scheduleDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: " + scheduleDTO.getUserId())));
        schedule.setTrainingDates(scheduleDTO.getTrainingDates().stream()
                .map(this::convertToEntity)
                .peek(trainingDate -> trainingDate.setSchedule(schedule))
                .collect(Collectors.toList()));
        schedule.setPlans(scheduleDTO.getPlans().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
    }
}
