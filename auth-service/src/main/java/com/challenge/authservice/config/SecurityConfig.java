package com.challenge.authservice.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
   private static final String[] SWAGGER_WHITELIST = {
         "/swagger-ui/**",
         "/v3/api-docs/**",
         "/swagger-resources/**",
         "/webjars/**"
   };

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/auth/signup", "/auth/login").permitAll()
                  .requestMatchers(SWAGGER_WHITELIST).permitAll()
                  .anyRequest().authenticated())
            .build();
   }
}
