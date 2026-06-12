package com.example.apkabazadanych2.junctionTables.trainingExerciseList;


import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.training.Training;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainingAddRequest {
    public Training training;
    public List<Long> exercisesIDs;
    public List<Long> reps;
}
