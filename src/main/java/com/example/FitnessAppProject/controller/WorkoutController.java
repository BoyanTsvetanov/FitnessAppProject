package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.exercise.ExerciseDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutHomeDTO;
import com.example.FitnessAppProject.models.entity.Workout;
import com.example.FitnessAppProject.service.ExerciseService;
import com.example.FitnessAppProject.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public String allWorkouts(Model model){
        WorkoutHomeDTO workoutHomeDTO = workoutService.getAllWorkouts();
        model.addAttribute("workouts", workoutHomeDTO);
        return ("workouts");
    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        WorkoutDTO workoutDTO = new WorkoutDTO();
        model.addAttribute("workoutDTO", workoutDTO);
        model.addAttribute("editMode", false);

        // Fetch all available exercises
        List<ExerciseDTO> availableExercises = exerciseService.getExerciseData().getAllExercises();
        model.addAttribute("availableExercises", availableExercises);

        return "workout-form"; // This returns workout_form.html template for creating new workout
    }

    // Handler method to handle form submission for creating a new workout
    @PostMapping("/create")
    public String createWorkout(@ModelAttribute("workoutDTO") WorkoutDTO workoutDTO, RedirectAttributes redirectAttributes) {
        // Convert selected exerciseIds to Set<Long>
        Set<Long> exerciseIds = workoutDTO.getExerciseIds();

        workoutDTO.setExerciseIds(exerciseIds);

        workoutService.createWorkout(workoutDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Workout created successfully!");
        return "redirect:/workouts";
    }


    // Handler method to show details of a specific workout
    @GetMapping("/{id}")
    public String showWorkoutDetails(@PathVariable Long id, Model model) {
        Optional<Workout> workoutOptional = workoutService.getWorkoutById(id);
        if (workoutOptional.isPresent()) {
            Workout workout = workoutOptional.get();
            WorkoutDTO workoutDTO = WorkoutDTO.createFromWorkout(workout);

            // Fetch exercises and set them to the DTO
            List<ExerciseDTO> exercises = workout.getExercises().stream()
                    .map(ExerciseDTO::createFromExercise)
                    .collect(Collectors.toList());
            workoutDTO.setExercises(exercises);

            model.addAttribute("workout", workoutDTO);
            return "workout-details"; // This returns workout_details.html template
        } else {
            // Handle workout not found scenario (redirect or show error page)
            return "redirect:/workouts"; // Redirect to the list of workouts
        }
    }

    // Handler method to show form for editing a workout
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Workout> workoutOptional = workoutService.getWorkoutById(id);
        if (workoutOptional.isPresent()) {
            WorkoutDTO workoutDTO = WorkoutDTO.createFromWorkout(workoutOptional.get());
            model.addAttribute("workoutDTO", workoutDTO);
            model.addAttribute("editMode", true);

            // Fetch all available exercises
            List<ExerciseDTO> availableExercises = exerciseService.getExerciseData().getAllExercises();
            model.addAttribute("availableExercises", availableExercises);

            return "workout-form"; // Assuming "workout_form.html" template exists
        } else {
            // Handle workout not found scenario (redirect or show error page)
            return "redirect:/workouts";
        }
    }

    // Handler to process form submission for updating a workout
    @PostMapping("/{id}/update")
    public String updateWorkout(@PathVariable Long id, @ModelAttribute("workoutDTO") WorkoutDTO workoutDTO, RedirectAttributes redirectAttributes) {
        workoutService.updateWorkout(id, workoutDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Workout updated successfully!");
        return "redirect:/workouts";
    }

    // Handler method to delete a workout
    @GetMapping("/{id}/delete")
    public String deleteWorkout(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        workoutService.deleteWorkout(id);
        redirectAttributes.addFlashAttribute("successMessage", "Workout deleted successfully!");
        return "redirect:/workouts"; // Redirect to the list of workouts after deletion
    }
}
