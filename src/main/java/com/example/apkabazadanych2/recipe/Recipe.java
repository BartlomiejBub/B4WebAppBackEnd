package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.product.Product;
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
@Table(name = "recipes")
public class Recipe {
    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )

    public Long ID_Recipe;

    public String name;
    public String header;
    public String description;

    public Long timeToCook;

    public Double caloric;
    public Double protein;
    public Double carbs;
    public Double fat;

    public Long gramsInOne;
    public Long portion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_User", nullable = false)
    public User user;

    public Long getUserID() {
        if (this.user != null) {
            return this.user.ID_User;
        }
        return null;
    }
}

