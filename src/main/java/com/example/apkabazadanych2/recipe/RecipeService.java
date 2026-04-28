package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {
    public RecipeRepository recipeRepository;
    public UserRepository userRepository;
    public RecipeProductListRepository recipeProductListRepository;

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

    @Transactional
    public void deleteRecipe(Long recipeID){
        recipeProductListRepository.deleteByRecipeId(recipeID);
        recipeRepository.deleteById(recipeID);
    }

}

