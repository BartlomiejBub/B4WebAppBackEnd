package com.example.apkabazadanych2.training;

import com.example.apkabazadanych2.junctionTables.trainingExerciseList.TrainingExerciseListRepository;
import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingService {

    private TrainingRepository trainingRepository;
    private TrainingExerciseListRepository trainingExerciseListRepository;
    private UserRepository userRepository;

    public Training addTraining(Training training, Long userID){
        training.user = userRepository.getReferenceById(userID);
        trainingRepository.save(training);
        return training;
    }

    public List<Training> getTrainings(){
        return trainingRepository.findAll();
    }

    public List<Training> getTrainingsByUser(Long userID){
        return trainingRepository.findAll();
    }

    @Transactional
    public void deleteTraining(Long trainingID){
        trainingExerciseListRepository.deleteByTrainingId(trainingID);
        trainingRepository.deleteById(trainingID);
    }

}
