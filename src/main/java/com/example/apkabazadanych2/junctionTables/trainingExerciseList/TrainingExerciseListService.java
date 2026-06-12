package com.example.apkabazadanych2.junctionTables.trainingExerciseList;

import com.example.apkabazadanych2.exercise.Exercise;
import com.example.apkabazadanych2.exercise.ExerciseRepository;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductList;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductRepository;
import com.example.apkabazadanych2.recipe.RecipeRepository;
import com.example.apkabazadanych2.training.TrainingRepository;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TrainingExerciseListService {
    public TrainingExerciseListRepository trainingExerciseListRepository;
    public TrainingRepository trainingRepository;
    public ExerciseRepository exerciseRepository;

    public void addTrainingExerciseList(List<Long> IDs, Long trainingID, List<Long> reps){
        int i = 0;
        for(Long id: IDs){
            trainingExerciseListRepository.save(
                    new TrainingExerciseList(
                            null,
                            exerciseRepository.getReferenceById(id),
                            trainingRepository.getReferenceById(trainingID),
                            reps.get(i),
                            reps.get(i) * exerciseRepository.getReferenceById(id).getCaloriesBurnedInRep()
                    )
            );
            i++;
        }
    }

    public List<Exercise> getTrainingExerciseList(Long trainingID){
        return trainingExerciseListRepository.findExercisesByTrainingID(trainingID);
    }

    public List<Long> getExercisesRepsByTrainingList(Long recipeID){
        return trainingExerciseListRepository.findExercisesRepsByTrainingID(recipeID);
    }

    public List<TrainingExerciseList> getTrainingExercisesList(){
        return trainingExerciseListRepository.findAll();
    }
}
