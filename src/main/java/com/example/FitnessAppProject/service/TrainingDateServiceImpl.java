package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import com.example.FitnessAppProject.models.entity.Plan;
import com.example.FitnessAppProject.models.entity.TrainingDate;
import com.example.FitnessAppProject.models.entity.User;
import com.example.FitnessAppProject.repo.PlanRepository;
import com.example.FitnessAppProject.repo.TrainingDateRepository;
import com.example.FitnessAppProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingDateServiceImpl implements TrainingDateService {

    @Autowired
    private TrainingDateRepository trainingDateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    @Transactional
    public void recordTraining(Long userId, Long planId, LocalDate trainingDate) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        TrainingDate trainingRecord = new TrainingDate();
        trainingRecord.setUser(user);
        trainingRecord.setPlan(plan);
        trainingRecord.setTrainingDate(trainingDate);

        trainingDateRepository.save(trainingRecord);
    }

    @Override
    public List<TrainingDateDTO> getTrainingDatesByUser(Long userId) {
        // Fetch training records for the specified user
        List<TrainingDate> trainingDates = trainingDateRepository.findByUserId(userId);

        // Convert the list of TrainingDate entities to TrainingDateDTOs
        return trainingDates.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TrainingDateDTO convertToDTO(TrainingDate trainingDate) {
        TrainingDateDTO trainingDateDTO = new TrainingDateDTO();
        trainingDateDTO.setId(trainingDate.getId());
        trainingDateDTO.setTrainingDate(trainingDate.getTrainingDate());
        trainingDateDTO.setUserId(trainingDate.getUser().getId());
        trainingDateDTO.setPlanId(trainingDate.getPlan().getId());
        return trainingDateDTO;
    }
}