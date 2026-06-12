package com.example.apkabazadanych2.training;


import com.example.apkabazadanych2.exercise.Exercise;
import com.example.apkabazadanych2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Training {
    @Id
    @SequenceGenerator(
            name = "training_sequence",
            sequenceName = "training_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "training_sequence"
    )
    public Long ID_Training;
    public Long timeNeeded;
    public String name;
    public String description;
    public String target;
    public Long caloriesBurnedInOne;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_User")
    public User user;

    public Long getUserID() {
        if (this.user != null) {
            return this.user.ID_User;
        }
        return null;
    }


}
