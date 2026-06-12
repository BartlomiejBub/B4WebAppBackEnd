package com.example.apkabazadanych2.exercise;

import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getExercises() {
        return exerciseService.getExercises();
    }

    @PostMapping(path = "/{userID}")
    public Exercise addNewExerciseWithUserIdentity(@RequestBody Exercise exercise, @PathVariable("userID") Long userID) {
        exerciseService.addNewExercise(exercise, userID);
        return exercise;
    }

    @DeleteMapping(path = "/{exerciseID}")
    public void deleteProduct(@PathVariable("exerciseID") Long exerciseID) {
        exerciseService.deleteExercise(exerciseID);
    }

    @PutMapping
    public void updateExercise(@RequestBody Exercise exercise) {
        exerciseService.updateExercise(exercise);
    }

}
