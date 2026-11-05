package com.example.apkabazadanych2.security;

import com.example.apkabazadanych2.security.config.JWT_Service;
import com.example.apkabazadanych2.user.LoginResponse;
import com.example.apkabazadanych2.user.User;
import com.example.apkabazadanych2.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWT_Service jwtService;
    private final UserService userService;

    public LoginResponse loginUser(String login, String password){

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            Optional<User> check = userService.getUser(login);
            if (check.isPresent()) {
                return jwtService.generateToken(login, check.get().email, check.get().userRole.toString(), check.get().ID_User);
            }
        } catch (AuthenticationException e) {
            return new LoginResponse("WRONG PASSWORD", null, null, null, null, null);
        }

        return null;
    }

}
