package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.plan.PlanDTO;
import com.example.FitnessAppProject.models.dto.plan.PlanHomeDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutDTO;
import com.example.FitnessAppProject.models.dto.workout.WorkoutHomeDTO;
import com.example.FitnessAppProject.service.PlanServiceImpl;
import com.example.FitnessAppProject.service.WorkoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/plans")
public class PlanController {
    private final PlanServiceImpl planService;
    private final WorkoutServiceImpl workoutService;

    @Autowired
    public PlanController(PlanServiceImpl planService, WorkoutServiceImpl workoutService) {
        this.planService = planService;
        this.workoutService = workoutService;
    }

    @GetMapping("/create")
    public String showCreatePlanForm(Model model) {
        model.addAttribute("editMode", false);
        model.addAttribute("planDTO", new PlanDTO());

        // Fetch all workouts
        WorkoutHomeDTO workoutHomeDTO = workoutService.getAllWorkouts();
        model.addAttribute("workouts", workoutHomeDTO.getAllWorkouts());

        return "plan-form";
    }

    @PostMapping("/create")
    public String createPlan(@ModelAttribute PlanDTO planDTO) {
        // Map selected workout IDs to workouts in planDTO

        List<WorkoutDTO> workoutDTOS = planDTO.getWorkoutIds().stream()
                .map(workoutId -> workoutService.getWorkoutById(workoutId)) // Get Optional<Workout>
                .filter(Optional::isPresent) // Filter out empty Optionals
                .map(Optional::get) // Get the Workout from Optional
                .map(WorkoutDTO::createFromWorkout) // Convert to WorkoutDTO
                .collect(Collectors.toList()); // Collect to List

        planDTO.setWorkouts(workoutDTOS);

        planService.createPlan(planDTO);
        return "redirect:/plans";
    }

    @GetMapping("/{id}/edit")
    public String showEditPlanForm(@PathVariable Long id, Model model) {
        PlanDTO planDTO = planService.getPlanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with id " + id));
        model.addAttribute("editMode", true);
        model.addAttribute("planDTO", planDTO);

        // Fetch all workouts
        WorkoutHomeDTO workoutHomeDTO = workoutService.getAllWorkouts();
        model.addAttribute("workouts", workoutHomeDTO.getAllWorkouts());

        return "plan-form";
    }



    @GetMapping("/all")
    public String getAllPlans(Model model) {
        PlanHomeDTO planHomeDTO = planService.getAllPlans();
        model.addAttribute("plans", planHomeDTO.getAllPlans());
        return "all-plans"; // Thymeleaf template name (all-plans.html)
    }

    @GetMapping("/{id}")
    public String viewPlan(@PathVariable Long id, Model model) {
        Optional<PlanDTO> planDTO = planService.getPlanById(id);
        if (planDTO.isPresent()) {
            model.addAttribute("plan", planDTO.get());
            return "plan-view";
        } else {
            return "redirect:/plans/all";
        }
    }

    @PostMapping("/{id}/update")
    public String updatePlan(@PathVariable Long id, @ModelAttribute PlanDTO planDTO) {
        planDTO.setWorkouts(planDTO.getWorkoutIds().stream()
                .map(workoutId -> workoutService.getWorkoutById(workoutId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(WorkoutDTO::createFromWorkout)
                .collect(Collectors.toList()));

        planService.updatePlan(id, planDTO);
        return "redirect:/plans";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePlan(@PathVariable Long id, Model model) {
        planService.deletePlan(id);
        model.addAttribute("message", "Plan with id " + id + " deleted successfully!");
        return "plan-deleted"; // Thymeleaf template name (plan-deleted.html)
    }
}
