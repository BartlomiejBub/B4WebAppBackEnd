package com.example.apkabazadanych2.junctionTables.productsByUsers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductsByUsersResponse {
    public Long idOfEntry;
    public String name;
    public Long caloriesCalculated;
    public Long proteinCalculated;
    public Long fatCalculated;
    public Long carbsCalculated;
    public Long weight;
    public Long time;
}
