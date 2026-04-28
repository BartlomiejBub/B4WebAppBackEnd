package com.example.apkabazadanych2.junctionTables.recipesByUsers;

import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductByUserAddRequest;
import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsersResponse;
import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/recipesByUser")
public class RecipesByUsersController {

    private final RecipesByUsersService recipesByUsersService;

    @Autowired
    public RecipesByUsersController(RecipesByUsersService recipesByUsersService){
        this.recipesByUsersService = recipesByUsersService;
    }

    @GetMapping(path="/{userID}/{date}")
    public List<ProductsByUsersResponse> getProductsByUsers(@PathVariable("date") LocalDate date, @PathVariable("userID") Long userID){
        return recipesByUsersService.findByUserAndDate(userID, date);
    }

    @PostMapping(path="add")
    public void addNewRecipeByUser(@RequestBody ProductByUserAddRequest productByUserAddRequest){
        recipesByUsersService.addNewRecipeByUsers(productByUserAddRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRecipeByUser(@PathVariable("id") Long id){
        recipesByUsersService.deleteRecipeByUsers(id);
    }

}
