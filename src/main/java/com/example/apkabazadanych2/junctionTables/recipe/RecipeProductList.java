package com.example.apkabazadanych2.junctionTables.recipe;

import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RecipeProductList")
@AllArgsConstructor
@NoArgsConstructor
public class RecipeProductList {

    @Id
    @SequenceGenerator(
            name = "RPL_sequence",
            sequenceName = "RPL_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "RPL_sequence"
    )

    public Long ID_RecipeProductList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_Recipe", nullable = false)
    public Recipe recipe;

    public Long getRecipeID() {
        if (this.recipe != null) {
            return this.recipe.ID_Recipe;
        }
        return null;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_Product", nullable = false)
    public Product product;

    public Long getProductID() {
        if (this.product != null) {
            return this.product.ID_Product;
        }
        return null;
    }

    public Long weight;
}
