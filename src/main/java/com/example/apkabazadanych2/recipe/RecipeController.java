package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductList;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListService;
import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/recipe")
public class RecipeController {

    private final RecipeProductListService recipeProductListService;
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeProductListService recipeProductListService, RecipeService recipeService, RecipeRepository recipeRepository){
        this.recipeProductListService = recipeProductListService;
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
    }


    @GetMapping
    public List<Recipe> getRecipes(){
        return recipeService.getRecipes();
    }

    @GetMapping(path = "/getProductsFromRecipe/{recipeID}")
    public List<Product> getProductsFromRecipe(@PathVariable("recipeID") Long recipeID){
        return recipeProductListService.getRecipeProductList(recipeID);
    }

    @GetMapping(path = "/getProductsFromRecipe")
    public List<RecipeProductList> getProductsFromRecipes(@PathVariable("recipeID") Long recipeID){
        return recipeProductListService.getRecipesProductList();
    }

    @PostMapping(path = "/{userID}")
    public Recipe addNewRecipe(@RequestBody RecipeAddRequest recipeAddRequest, @PathVariable("userID") Long userID){
        Recipe addedRecipe =  recipeService.addRecipe(recipeAddRequest.recipe, userID);
        recipeProductListService.addRecipeProductList(recipeAddRequest.productIDs, addedRecipe.ID_Recipe);
        return recipeAddRequest.recipe;
    }

    @DeleteMapping(path = "/{recipeID}") // do zrobienia!!!!!!
    public void deleteRecipe(@PathVariable("recipeID") Long recipeID){

    }

}
