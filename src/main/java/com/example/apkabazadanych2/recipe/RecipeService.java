package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductList;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.junctionTables.recipesByUsers.RecipesByUsersRepository;
import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RecipeService {
    public RecipeRepository recipeRepository;
    public UserRepository userRepository;
    public RecipeProductListRepository recipeProductListRepository;
    public RecipesByUsersRepository recipesByUsersRepository;

    public Recipe addRecipe(Recipe recipe, Long userID){
        recipe.user = userRepository.getReferenceById(userID);
        recipeRepository.save(recipe);
        return recipe;
    }

    public List<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<RecipeWithProductsResponse> getRecipesWithProducts(){
        Map<Long, List<Product>> productsByRecipe = new HashMap<>();
        Map<Long, List<Long>> weightsByRecipe = new HashMap<>();
        for (RecipeProductList row : recipeProductListRepository.findAllOrdered()) {
            Long recipeID = row.getRecipeID();
            productsByRecipe.computeIfAbsent(recipeID, id -> new ArrayList<>()).add(row.product);
            weightsByRecipe.computeIfAbsent(recipeID, id -> new ArrayList<>()).add(row.weight);
        }

        List<RecipeWithProductsResponse> result = new ArrayList<>();
        for (Recipe recipe : recipeRepository.findAll()) {
            result.add(new RecipeWithProductsResponse(
                    recipe,
                    productsByRecipe.getOrDefault(recipe.ID_Recipe, List.of()),
                    weightsByRecipe.getOrDefault(recipe.ID_Recipe, List.of())
            ));
        }
        return result;
    }

    @Transactional
    public void deleteRecipe(Long recipeID){
        if (recipesByUsersRepository.existsByRecipeID(recipeID)) {
            throw new IllegalStateException("Cannot delete the recipe - it is saved in the eaten meals history");
        }
        recipeProductListRepository.deleteByRecipeId(recipeID);
        recipeRepository.deleteById(recipeID);
    }

}
