package com.example.apkabazadanych2.junctionTables.recipesByUsers;

import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductByUserAddRequest;
import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsers;
import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsersRepository;
import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsersResponse;
import com.example.apkabazadanych2.product.ProductRepository;
import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.recipe.RecipeRepository;
import com.example.apkabazadanych2.user.User;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class RecipesByUsersService {

    private final RecipesByUsersRepository recipeByUsersRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public List<ProductsByUsersResponse> findByUserAndDate(Long userID, LocalDate date){
        User user = userRepository.findById(userID).orElseThrow( () -> new IllegalStateException("Nie znaleziono"));
        List<RecipesByUsers> listOfRaw = recipeByUsersRepository.findByUserAndDate(user, date);
        return listOfRaw.stream().map(row -> {
                    // Makroskładniki przepisu są podane dla całego przepisu (porcje * waga porcji)
                    long totalGrams = recipeTotalGrams(row.getRecipe());
                    return new ProductsByUsersResponse(
                            row.getID_recipesByUsers(),
                            row.getRecipe().name,
                            ProductsByUsersResponse.round1(row.getRecipe().caloric * row.getWeight() / totalGrams),
                            ProductsByUsersResponse.round1(row.getRecipe().protein * row.getWeight() / totalGrams),
                            ProductsByUsersResponse.round1(row.getRecipe().fat * row.getWeight() / totalGrams),
                            ProductsByUsersResponse.round1(row.getRecipe().carbs * row.getWeight() / totalGrams),
                            row.getWeight(),
                            row.getTime()
                    );
                }
        ).toList();
    }

    private long recipeTotalGrams(Recipe recipe){
        long portion = recipe.portion != null ? recipe.portion : 1;
        long gramsInOne = recipe.gramsInOne != null ? recipe.gramsInOne : 0;
        long total = portion * gramsInOne;
        // Brak wagi przepisu - traktujemy makro jak na 100g, żeby nie dzielić przez 0
        return total > 0 ? total : 100;
    }

    public void deleteRecipeByUsers(Long recipeID){
        recipeByUsersRepository.deleteById(recipeID);
    }

    public void addNewRecipeByUsers(ProductByUserAddRequest productByUserAddRequest){
        RecipesByUsers recipeByUser = new RecipesByUsers(null, userRepository.getReferenceById(productByUserAddRequest.userID), recipeRepository.getReferenceById(productByUserAddRequest.productID), productByUserAddRequest.date, productByUserAddRequest.weight, productByUserAddRequest.time);
        recipeByUsersRepository.save(recipeByUser);
    }

}

