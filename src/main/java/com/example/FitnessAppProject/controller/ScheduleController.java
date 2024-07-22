package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.schedule.ScheduleDTO;
import com.example.FitnessAppProject.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public String getAllSchedules(Model model) {
        List<ScheduleDTO> schedules = scheduleService.findAll();
        model.addAttribute("schedules", schedules);
        return "schedule-list";  // Return view name
    }

    @GetMapping("/{id}")
    public String getScheduleById(@PathVariable Long id, Model model) {
        ScheduleDTO schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        return "schedule-detail";  // Return view name
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ScheduleDTO schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        return "schedule-form";  // Return view name
    }

    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute ScheduleDTO scheduleDTO) {
        scheduleService.updateSchedule(id, scheduleDTO);
        return "redirect:/schedules";  // Redirect to the list view
    }

    @PostMapping("/{scheduleId}/plans/{planId}/add")
    public String addPlanToSchedule(@PathVariable Long scheduleId, @PathVariable Long planId) {
        scheduleService.addPlanToSchedule(scheduleId, planId);
        return "redirect:/schedules/" + scheduleId;  // Redirect to the schedule detail view
    }

    @PostMapping("/{scheduleId}/plans/{planId}/remove")
    public String removePlanFromSchedule(@PathVariable Long scheduleId, @PathVariable Long planId) {
        scheduleService.removePlanFromSchedule(scheduleId, planId);
        return "redirect:/schedules/" + scheduleId;  // Redirect to the schedule detail view
    }
}
