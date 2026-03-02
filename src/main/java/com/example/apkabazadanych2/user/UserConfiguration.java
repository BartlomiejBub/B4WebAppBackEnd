package com.example.apkabazadanych2.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Calendar.JANUARY;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User Bartek = new User("Bartek", "Bubicz", "bambik2115", "haslo", "email", LocalDate.now() );
            User Broski = new User("Broski", "Bubi", "bambik3115", "haslo", "email2", LocalDate.of(2004, 1, 25));
            userRepository.saveAll(
                    List.of(Bartek, Broski)
            );
        };
    }
}
