package com.example.apkabazadanych2.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// Dzienne cele użytkownika (kcal i makroskładniki w gramach)
@AllArgsConstructor
@NoArgsConstructor
public class UserGoals {
    public Double goalKcal;
    public Double goalProtein;
    public Double goalFat;
    public Double goalCarbs;
}
