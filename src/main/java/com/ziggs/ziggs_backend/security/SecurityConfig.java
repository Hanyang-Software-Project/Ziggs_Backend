package com.ziggs.ziggs_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Allow GET requests to /api/users/userDTO/{id} without authentication
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()  // Allow POST requests to /api/users without authentication
                        .requestMatchers(HttpMethod.GET, "/api/users/userDTO/*").permitAll()  // Allow GET requests to /api/users/userDTO/{id} without authentication
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .httpBasic(withDefaults());  // Enable HTTP Basic auth with defaults

        return http.build();
    }
}

