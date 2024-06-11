package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.enums.MuscleGroupEnum;
import com.example.FitnessAppProject.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("exercise", new ExerciseDTO());
        model.addAttribute("muscleGroups", MuscleGroupEnum.values());
        return "create-exercise";
    }

    @PostMapping
    public String createExercise(@ModelAttribute ExerciseDTO exerciseDTO) {
        exerciseService.createExercise(exerciseDTO);
        return "redirect:/exercises";
    }

    // Other methods...
}
