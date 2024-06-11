package com.example.FitnessAppProject.service;

import com.example.FitnessAppProject.models.dto.user.UserRegisterBindingDto;
import com.example.FitnessAppProject.models.entity.RoleEntity;
import com.example.FitnessAppProject.models.entity.User;
import com.example.FitnessAppProject.models.enums.UserRoleEnum;
import com.example.FitnessAppProject.repo.RoleRepository;
import com.example.FitnessAppProject.repo.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean register(UserRegisterBindingDto userRegisterBindingDto) throws Exception {

        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(
                userRegisterBindingDto.getUsername(), userRegisterBindingDto.getEmail()
        );

        if (existsByUsernameOrEmail){
            return false;
        }


        User user = new User();
        user.setUsername(userRegisterBindingDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterBindingDto.getPassword()));
        user.setEmail(userRegisterBindingDto.getEmail());
        user.setAge(userRegisterBindingDto.getAge());
        user.setGender(userRegisterBindingDto.getGender());
        user.setHeight(userRegisterBindingDto.getHeight());
        user.setWeight(userRegisterBindingDto.getWeight());


        UserRoleEnum defaultRole = UserRoleEnum.USER;
        RoleEntity role = roleRepository.findByRole(defaultRole)
                .orElseThrow(() -> new IllegalArgumentException("Default role not found"));

        // Set the roles for the user
        user.getRoles().add(role);

        user = userRepository.save(user);

        return true;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof org.springframework.security.core.userdetails.User) {
                // Spring Security User object, extract the username
                String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();

                // Assuming you have a method to get the user by username returning Optional<UserEntity>
                Optional<User> userEntityOptional = userRepository.getByUsername(username);

                if (userEntityOptional.isPresent()) {
                    User user = userEntityOptional.get();
                    // Force initialization of collections if necessary
                    Hibernate.initialize(user.getClass());
                    // Now you can safely access the collections
                }
                // Return the UserEntity if present in the Optional
                return userEntityOptional.orElse(null);
            }
        }

        return null;
    }
}
