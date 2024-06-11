package com.example.FitnessAppProject.init;

import com.example.FitnessAppProject.models.entity.RoleEntity;
import com.example.FitnessAppProject.models.enums.UserRoleEnum;
import com.example.FitnessAppProject.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RolesInit implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public RolesInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = roleRepository.count();

        if (count == 0){
            List<RoleEntity> roles = new ArrayList<>();

            Arrays.stream(UserRoleEnum.values())
                    .forEach(roleEnum -> {
                        RoleEntity role = new RoleEntity();
                        role.setRole(roleEnum);
                        roles.add(role);
                    });

            roleRepository.saveAll(roles);
        }
    }
}
