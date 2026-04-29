package com.example.apkabazadanych2.security.config;

import com.example.apkabazadanych2.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(BCryptPasswordEncoder bcryptPasswordEncoder, UserDetailsService userDetailsService, UserRepository userRepository) {
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v*/user/**").permitAll()
                        .requestMatchers("/api/v*/user").permitAll()
                        .requestMatchers("/api/v*/registration/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/user/login/try").permitAll()
                        .requestMatchers("/api/v*/product/**").permitAll()
                        .requestMatchers("/api/v*/product/").permitAll()
                        .requestMatchers("/api/v*/product").permitAll()
                        .requestMatchers("/api/v1/productsByUser").permitAll()
                        .requestMatchers("/api/v1/productsByUser/add").permitAll()
                        .requestMatchers("/api/v1/productsByUser/**").permitAll()
                        .requestMatchers("/api/v1/productsCreatedByUsers/**").permitAll()
                        .requestMatchers("/api/v1/productsCreatedByUsers").permitAll()
                        .requestMatchers("/api/v1/recipe/").permitAll()
                        .requestMatchers("/api/v1/recipe/**").permitAll()
                        .requestMatchers("/api/v1/recipe/getProductsFromRecipe/").permitAll()
                        .requestMatchers("/api/v1/recipe/getProductsFromRecipe/**").permitAll()
                        .requestMatchers("/api/v1/recipesByUser/**").permitAll()
                        .requestMatchers("/api/v1/recipesByUser").permitAll()
                        .requestMatchers("/api/v1/recipesByUser/add").permitAll()
                        .requestMatchers("/api/v1/recipesByUser/add/**").permitAll()




                        .requestMatchers("/error").permitAll()

                        .anyRequest().authenticated()
                )
        ;

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(bcryptPasswordEncoder);

        return provider;
    }

}