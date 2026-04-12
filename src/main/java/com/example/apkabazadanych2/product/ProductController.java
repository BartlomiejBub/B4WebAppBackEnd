package com.example.apkabazadanych2.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping(path = "/{userID}")
    public Product addNewProductWithUserIdentity(@RequestBody Product product, @PathVariable("userID") Long userID) {
        productService.addNewProduct(product, userID);
        return product;
    }

    @DeleteMapping(path = "/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productID) {
        productService.deleteProduct(productID);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

}