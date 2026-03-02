package com.example.apkabazadanych2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user){
        Optional<User> userByLogin = userRepository.findByLogin(user.getLogin());

        if(userByLogin.isPresent()) {
            throw new IllegalStateException("jest juz taki");
        } else {
            userRepository.save(user);
        }
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
           throw new IllegalStateException("User with id " + userId + " dont exists");
        } else {
            userRepository.deleteById(userId);
        }
    }

    public void updateUser(Long userId, String name, String email) {
    }
}
