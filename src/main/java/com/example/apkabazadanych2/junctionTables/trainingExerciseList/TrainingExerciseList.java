package com.example.apkabazadanych2.junctionTables.trainingExerciseList;

import com.example.apkabazadanych2.exercise.Exercise;
import com.example.apkabazadanych2.training.Training;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TrainingExerciseList")
@AllArgsConstructor
@NoArgsConstructor
public class TrainingExerciseList {
    @Id
    @SequenceGenerator(
            name = "trainingexercise_sequence",
            sequenceName = "trainingexercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trainingexercise_sequence"
    )
    public Long ID_TrainingExerciseList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_Exercise")
    public Exercise exercise;

    public Long getExerciseID() {
        if (this.exercise != null) {
            return this.exercise.ID_Exercise;
        }
        return null;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_Training")
    public Training training;

    public Long getTrainingID() {
        if (this.training != null) {
            return this.training.ID_Training;
        }
        return null;
    }


    public Long reps;
    public Long kcalBurned;

}
