package com.ShopMinds;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
    .csrf(csrf -> csrf.disable())
    .cors(cors -> {})
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/health").permitAll() // allow health check
        .anyRequest().authenticated()              // everything else requires login
    );


        return http.build();
    }
}
