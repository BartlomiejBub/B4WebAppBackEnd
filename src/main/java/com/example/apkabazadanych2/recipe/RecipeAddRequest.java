package com.example.apkabazadanych2.recipe;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeAddRequest {
    public Recipe recipe;
    public List<Long> productIDs;
    public List<Long> weights;
}
