package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    public RecipeRepository recipeRepository;
    public UserRepository userRepository;

    public Recipe addRecipe(Recipe recipe, Long userID){
        recipe.user = userRepository.getReferenceById(userID);
        recipeRepository.save(recipe);
        return recipe;
    }

    public List<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }

    public List<Recipe> getRecipesByUser(Long userID){
        return recipeRepository.findAll();
    }
}
