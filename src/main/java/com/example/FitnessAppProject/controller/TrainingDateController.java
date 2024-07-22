package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.dates.TrainingDateDTO;
import com.example.FitnessAppProject.service.TrainingDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/training")
public class TrainingDateController {

    @Autowired
    private TrainingDateService trainingDateService;

    @GetMapping("/record")
    public String showRecordForm(Model model) {
        model.addAttribute("trainingDateForm", new TrainingDateDTO());
        return "record-training";
    }

    @PostMapping("/record")
    public String recordTraining(@ModelAttribute TrainingDateDTO form) {
        trainingDateService.recordTraining(form.getUserId(), form.getPlanId(), form.getTrainingDate());
        return "redirect:/training/history";
    }

    @GetMapping("/history")
    public String getTrainingHistory(@RequestParam Long userId, Model model) {
        List<TrainingDateDTO> trainingDates = trainingDateService.getTrainingDatesByUser(userId);
        model.addAttribute("trainingDates", trainingDates);
        return "training-history";
    }
}
