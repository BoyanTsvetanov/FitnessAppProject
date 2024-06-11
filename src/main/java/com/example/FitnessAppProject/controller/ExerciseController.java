package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.dto.exercise.ExerciseHomeDTO;
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

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        ExerciseHomeDTO exerciseHomeDTO = exerciseService.getExerciseData();
        ExerciseDTO exercise = exerciseHomeDTO.getAllExercises().stream()
                .filter(ex -> ex.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
        model.addAttribute("exercise", exercise);
        model.addAttribute("muscleGroups", MuscleGroupEnum.values());
        return "update-exercise";
    }

    @PostMapping("/{id}/update")
    public String updateExercise(@PathVariable Long id, @ModelAttribute ExerciseDTO exerciseDTO) {
        exerciseService.updateExercise(id, exerciseDTO);
        return "redirect:/exercises";
    }

    @GetMapping
    public String listExercises(Model model) {
        ExerciseHomeDTO exerciseHomeDTO = exerciseService.getExerciseData();
        model.addAttribute("exercises", exerciseHomeDTO.getAllExercises());
        return "exercises";
    }

    @GetMapping("/{id}")
    public String viewExercise(@PathVariable Long id, Model model) {
        ExerciseHomeDTO exerciseHomeDTO = exerciseService.getExerciseData();
        ExerciseDTO exercise = exerciseHomeDTO.getAllExercises().stream()
                .filter(ex -> ex.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + id));
        model.addAttribute("exercise", exercise);
        return "view-exercise";
    }

    @PostMapping("/{id}/delete")
    public String deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return "redirect:/exercises";
    }
}
