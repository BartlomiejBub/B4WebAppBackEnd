package com.example.apkabazadanych2.recipe;

import com.example.apkabazadanych2.product.Product;
import lombok.AllArgsConstructor;

import java.util.List;

// Recipe together with its ingredients - products.get(i) has weight weights.get(i)
@AllArgsConstructor
public class RecipeWithProductsResponse {
    public Recipe recipe;
    public List<Product> products;
    public List<Long> weights;
}
