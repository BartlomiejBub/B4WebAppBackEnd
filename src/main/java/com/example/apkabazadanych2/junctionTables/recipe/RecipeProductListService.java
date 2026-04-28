package com.example.apkabazadanych2.junctionTables.recipe;

import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductRepository;
import com.example.apkabazadanych2.recipe.RecipeRepository;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeProductListService {
    public RecipeProductListRepository recipeProductListRepository;
    public ProductRepository productRepository;
    public UserRepository userRepository;
    public RecipeRepository recipeRepository;

    public void addRecipeProductList(List<Long> IDs, Long recipeID, List<Long> weights){
        int i = 0;
        for(Long id: IDs){
            recipeProductListRepository.save(
                    new RecipeProductList(
                            null,
                        recipeRepository.getReferenceById(recipeID),
                        productRepository.getReferenceById(id),
                            weights.get(i)
                    )
            );
            i++;
        }
    }

    public List<Product> getRecipeProductList(Long recipeID){
        return recipeProductListRepository.findProductsByRecipeID(recipeID);
    }

    public List<Long> getRecipeProductWeightList(Long recipeID){
        return recipeProductListRepository.findProductsWeightsByRecipeID(recipeID);
    }

    public List<RecipeProductList> getRecipesProductList(){
        return recipeProductListRepository.findAll();
    }
}
