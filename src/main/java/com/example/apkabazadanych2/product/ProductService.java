package com.example.apkabazadanych2.product;

import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsersRepository;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductsByUsersRepository productsByUsersRepository;
    private final RecipeProductListRepository recipeProductListRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long productID){
        if (recipeProductListRepository.existsByProductID(productID)) {
            throw new IllegalStateException("Nie można usunąć produktu - jest składnikiem przepisu");
        }
        if (productsByUsersRepository.existsByProductID(productID)) {
            throw new IllegalStateException("Nie można usunąć produktu - jest zapisany w historii zjedzonych posiłków");
        }
        productRepository.deleteById(productID);
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }

    public void addNewProduct(Product product, Long userID){
        product.setUser(userRepository.findById(userID).orElseThrow());
        productRepository.save(product);
    }

}
