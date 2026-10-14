//package com.example.apkabazadanych2.junctionTables.exercisesByUsers;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.Date;
//
//@Table(name = "exercisesByUsers")
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class ExercisesByUsers {
//    @Id
//    @SequenceGenerator(
//            name = "exByUs_sequence",
//            sequenceName = "exByUs_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "exByUs_sequence"
//    )
//
//    public long ID_ExercisesByUsers;
//
//    @JsonIgnore
//    @JoinColumn(name = "ID_User", nullable = false)
//    @ManyToOne
//    private Long ID_User;
//
//    public Long reps;
//    public Double weight;
//    public LocalDate date;
//}
