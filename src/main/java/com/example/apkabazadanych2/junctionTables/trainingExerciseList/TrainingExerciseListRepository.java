package com.example.apkabazadanych2.junctionTables.trainingExerciseList;

import com.example.apkabazadanych2.exercise.Exercise;
import com.example.apkabazadanych2.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TrainingExerciseListRepository extends JpaRepository<TrainingExerciseList, Long> {
    @Query("SELECT t.exercise FROM TrainingExerciseList t WHERE t.training.ID_Training = :id")
    List<Exercise> findExercisesByTrainingID(@Param("id") Long id);

    @Query("SELECT t.reps FROM TrainingExerciseList t WHERE t.training.ID_Training = :id")
    List<Long> findExercisesRepsByTrainingID(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM TrainingExerciseList t WHERE t.training.ID_Training = :id")
    void deleteByTrainingId(@Param("id") Long id);
}
