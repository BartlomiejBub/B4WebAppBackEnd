package com.example.apkabazadanych2.exercise;


import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductRepository;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExerciseService {
    public final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public List<Exercise> getExercises(){
        return exerciseRepository.findAll();
    }

    public void deleteExercise(Long exerciseID){
        exerciseRepository.deleteById(exerciseID);
    }

    public void updateExercise(Exercise exercise){
        Optional<Exercise> productOptional = exerciseRepository.findById(exercise.ID_Exercise);
        exercise.setDescription(exercise.description);
        exercise.setName(exercise.name);
        exercise.setTarget(exercise.target);
        exercise.setCaloriesBurnedInRep(exercise.caloriesBurnedInRep);
        exerciseRepository.save(exercise);
    }

    public void addNewExercise(Exercise exercise, Long userID){
        exercise.setUser(userRepository.findById(userID).orElseThrow());
        exerciseRepository.save(exercise);
    }

}
