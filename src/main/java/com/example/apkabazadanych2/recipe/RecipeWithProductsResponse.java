package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.product.Product;
import lombok.AllArgsConstructor;

import java.util.List;

// Przepis razem ze składnikami - products.get(i) ma wagę weights.get(i)
@AllArgsConstructor
public class RecipeWithProductsResponse {
    public Recipe recipe;
    public List<Product> products;
    public List<Long> weights;
}
