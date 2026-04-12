package com.example.apkabazadanych2.junctionTables.productsByUsers;

import com.example.apkabazadanych2.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/productsByUser")
public class ProductsByUsersController {

    private final ProductsByUsersService productsByUsersService;

    @Autowired
    public ProductsByUsersController(ProductsByUsersService productsByUsersService){
        this.productsByUsersService = productsByUsersService;
    }

    @GetMapping(path="/{userID}/{date}")
    public List<ProductsByUsersResponse> getProductsByUsers(@PathVariable("date") LocalDate date, @PathVariable("userID") Long userID){
        return productsByUsersService.findByUserAndDate(userID, date);
    }

    @PostMapping(path="add")
    public void addNewProduct(@RequestBody ProductByUserAddRequest productByUserAddRequest){
        productsByUsersService.addNewProductByUsers(productByUserAddRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productsByUsersService.deleteProductByUsers(id);
    }

}