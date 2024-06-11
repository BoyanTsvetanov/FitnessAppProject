package com.example.FitnessAppProject.init;

import com.example.FitnessAppProject.models.entity.MuscleGroup;
import com.example.FitnessAppProject.models.enums.MuscleGroupEnum;
import com.example.FitnessAppProject.repo.MuscleGroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MuscleGroupInit implements CommandLineRunner {
    private final MuscleGroupRepository muscleGroupRepository;

    public MuscleGroupInit(MuscleGroupRepository muscleGroupRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = muscleGroupRepository.count();

        if (count == 0){
            List<MuscleGroup> muscleGroups = new ArrayList<>();

            Arrays.stream(MuscleGroupEnum.values())
                    .forEach(muscleGroupEnum -> {
                        MuscleGroup muscleGroup = new MuscleGroup();
                        muscleGroup.setMuscleGroup(muscleGroupEnum);
                        muscleGroups.add(muscleGroup);
                    });

            muscleGroupRepository.saveAll(muscleGroups);
        }
    }
}

