package com.example.apkabazadanych2.registration;

import com.example.apkabazadanych2.user.User;
import com.example.apkabazadanych2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @GetMapping(path = "confirm")
    public String confirmRegistrationToken(@RequestParam("token") String token){
        return userService.confirmRegistrationToken(token);
    }
}
