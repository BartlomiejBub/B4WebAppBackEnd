package com.example.apkabazadanych2.junctionTables.productsByUsers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductsByUsersResponse {
    public Long idOfEntry;
    public String name;
    public Double caloriesCalculated;
    public Double proteinCalculated;
    public Double fatCalculated;
    public Double carbsCalculated;
    public Long weight;
    public Long time;

    // Zaokrąglenie do 1 miejsca po przecinku (np. 33.333 -> 33.3)
    public static Double round1(double value) {
        return Math.round(value * 10) / 10.0;
    }
}
