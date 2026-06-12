package com.example.apkabazadanych2.exercise;


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
@Table(name = "exercises")
public class Exercise {
    @Id
    @SequenceGenerator(
            name = "exercise_sequence",
            sequenceName = "exercise_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exercise_sequence"
    )

    public Long ID_Exercise;
    public String name;
    public String description;
    public String target;
    public Long caloriesBurnedInRep;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_User")
    private User user;

    public Long getUserID() {
        if (this.user != null) {
            return this.user.ID_User;
        }
        return null;
    }
}
