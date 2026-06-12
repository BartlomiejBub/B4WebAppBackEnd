package com.example.apkabazadanych2.training;


import com.example.apkabazadanych2.exercise.Exercise;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductList;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListService;
import com.example.apkabazadanych2.junctionTables.trainingExerciseList.TrainingAddRequest;
import com.example.apkabazadanych2.junctionTables.trainingExerciseList.TrainingExerciseList;
import com.example.apkabazadanych2.junctionTables.trainingExerciseList.TrainingExerciseListRepository;
import com.example.apkabazadanych2.junctionTables.trainingExerciseList.TrainingExerciseListService;
import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductService;
import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.recipe.RecipeAddRequest;
import com.example.apkabazadanych2.recipe.RecipeRepository;
import com.example.apkabazadanych2.recipe.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/training")
@AllArgsConstructor
public class TrainingController {

    private final TrainingExerciseListService trainingExerciseListService;
    private final TrainingRepository trainingRepository;
    private final TrainingService trainingService;

    @GetMapping
    public List<Training> getTrainings(){
        return trainingService.getTrainings();
    }

    @GetMapping(path = "/getExercisesFromTraining/{trainingID}")
    public List<Exercise> getProductsFromRecipe(@PathVariable("trainingID") Long trainingID){
        return trainingExerciseListService.getTrainingExerciseList(trainingID);
    }

    @GetMapping(path = "/getExercisesRepsFromRecipe/{trainingID}")
    public List<Long> getProductsWeightsFromRecipe(@PathVariable("trainingID") Long trainingID){
        return trainingExerciseListService.getExercisesRepsByTrainingList(trainingID);
    }

    @GetMapping(path = "/getExercisesFromTraining")
    public List<TrainingExerciseList> getExercisesFromTrainings(){
        return trainingExerciseListService.getTrainingExercisesList();
    }

    @PostMapping(path = "/{userID}")
    public Training addNewTraining(@RequestBody TrainingAddRequest trainingAddRequest, @PathVariable("userID") Long userID){
        Training addedTraining =  trainingService.addTraining(trainingAddRequest.training, userID);
        trainingExerciseListService.addTrainingExerciseList(trainingAddRequest.exercisesIDs, addedTraining.ID_Training, trainingAddRequest.reps);
        return trainingAddRequest.training;
    }

    @DeleteMapping(path = "/{trainingID}")
    public void deleteRecipe(@PathVariable("trainingID") Long trainingID){
        trainingService.deleteTraining(trainingID);
    }

}

