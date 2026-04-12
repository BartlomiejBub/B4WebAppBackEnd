package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
