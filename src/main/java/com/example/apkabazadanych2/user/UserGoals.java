package com.example.apkabazadanych2.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// User's daily goals (kcal and macros in grams)
@AllArgsConstructor
@NoArgsConstructor
public class UserGoals {
    public Double goalKcal;
    public Double goalProtein;
    public Double goalFat;
    public Double goalCarbs;
}
