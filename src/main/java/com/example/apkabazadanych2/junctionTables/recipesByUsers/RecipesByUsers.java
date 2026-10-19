package com.example.apkabazadanych2.junctionTables.recipesByUsers;


import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "recipesByUsers")
public class RecipesByUsers {
    @Id
    @SequenceGenerator(
            name = "recipesByUsers_sequence",
            sequenceName = "recipesByUsers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipesByUsers_sequence"
    )
    public Long ID_recipesByUsers;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_User", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_Recipe", nullable = false)
    private Recipe recipe;

    public LocalDate date;
    public Long weight;
    public Long time;


}

