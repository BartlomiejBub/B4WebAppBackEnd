package com.example.apkabazadanych2.security.config;

import com.example.apkabazadanych2.user.LoginResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWT_Service {
    @Value("${JWT_SECRET:DefaultJsonWebTokenKeyYouNeedToSetThisInEnvFile}")
    private String key;

    public LoginResponse generateToken(String username, String email, String userRole, Long userID) {
        Map<String, Object> claims = new HashMap<>();

        return new LoginResponse("SUCCESS", username, email, userRole, userID,
        Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +1000 * 60 * 60 * 3  ))
                .and()
                .signWith(getKey())
                .compact()
        );
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
