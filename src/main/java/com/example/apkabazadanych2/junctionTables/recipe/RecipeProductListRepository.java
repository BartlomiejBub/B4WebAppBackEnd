package com.example.apkabazadanych2.junctionTables.recipe;

import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeProductListRepository extends JpaRepository<RecipeProductList, Long> {
    @Query("SELECT r.product FROM RecipeProductList r WHERE r.recipe.ID_Recipe = :id")
    List<Product> findProductsByRecipeID(@Param("id") Long id);

    @Query("SELECT r.weight FROM RecipeProductList r WHERE r.recipe.ID_Recipe = :id")
    List<Long> findProductsWeightsByRecipeID(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM RecipeProductList r WHERE r.recipe.ID_Recipe = :id")
    void deleteByRecipeId(@Param("id") Long id);
}


