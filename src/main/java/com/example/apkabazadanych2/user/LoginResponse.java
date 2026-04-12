package com.example.apkabazadanych2.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponse{
    public String response;
    public String login;
    public String email;
    public String userRole;
    public Long userID;
}