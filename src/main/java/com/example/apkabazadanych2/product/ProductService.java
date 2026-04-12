package com.example.apkabazadanych2.product;

import com.example.apkabazadanych2.user.User;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long productID){
        productRepository.deleteById(productID);
    }

    public void updateProduct(Product product){
        Optional<Product> productOptional = productRepository.findById(product.ID_Product);
        product.setName(product.name);
        product.setProtein(product.protein);
        product.setCarbs(product.carbs);
        product.setFat(product.fat);
        product.setGramsInOne(product.gramsInOne);
        product.setCaloric(product.caloric);
        product.setCode(product.code);
        productRepository.save(product);
    }

    public void addNewProduct(Product product, Long userID){
        product.setUser(userRepository.findById(userID).orElseThrow());
        productRepository.save(product);
    }

}
