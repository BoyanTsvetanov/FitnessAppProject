package com.example.FitnessAppProject.controller;

import com.example.FitnessAppProject.models.dto.user.UserRegisterBindingDto;
import com.example.FitnessAppProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserDetailsService userDetailsService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {

        return ("login");
    }

    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("username") String username,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", "true");

        return "login";
    }


    @GetMapping("/register")
    public String register(@ModelAttribute("userRegistrationDTO") UserRegisterBindingDto userRegistrationDTO) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userRegistrationDTO") @Valid UserRegisterBindingDto userRegistrationDTO,
                           BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            // Return the registration form with validation errors
            model.addAttribute("userRegistrationDTO", userRegistrationDTO); // Add the model attribute back to the model
            return "register";
        }
        boolean registrationSuccess = userService.register(userRegistrationDTO);

        if (registrationSuccess) {
            // Authenticate the user after registration
            authenticateUser(userRegistrationDTO.getUsername(), userRegistrationDTO.getPassword());
            // Redirect the user based on their subscription plan
            return "redirect:/index";
        } else {
            model.addAttribute("registration_error", "true");
            return "register";
        }
    }



    private void authenticateUser(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
