package com.example.apkabazadanych2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private BCryptPasswordEncoder bcryptPasswordEncoder;

    // 1. Zastępuje metodę configure(HttpSecurity http) ze screena nr 2
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Nowy sposób wyłączania CSRF
                .cors(Customizer.withDefaults())       // Odblokowuje CORS, żeby Twój panel HTML działał (rozwiązuje błąd Failed to fetch!)
                .authorizeHttpRequests(auth -> auth    // Zamiast authorizeRequests()
                        .requestMatchers("/api/v*/registration/**").permitAll() // Zamiast antMatchers()

                        // UWAGA: Jeśli chcesz, żeby Twój stary panel HTML od userów działał bez logowania,
                        // odkomentuj poniższą linijkę:
                         //.requestMatchers("/api/v1/user/**").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()); // Nowy sposób włączania domyślnego formularza logowania

        return http.build();
    }

    @Bean
    // Zauważ, że dodaliśmy UserDetailsService jako argument metody! Spring sam go tu magicznie wstrzyknie.
    public DaoAuthenticationProvider daoAuthenticationProvider(org.springframework.security.core.userdetails.UserDetailsService userDetailsService, BCryptPasswordEncoder bcryptPasswordEncoder) {

        // Używamy nowego konstruktora - od razu przekazujemy mu serwis użytkowników
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        // Ustawiamy szyfrator haseł
        provider.setPasswordEncoder(bcryptPasswordEncoder);

        return provider;
    }
}