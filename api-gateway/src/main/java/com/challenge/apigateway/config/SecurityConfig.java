package com.challenge.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

   @Bean
   public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
      return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchange -> exchange
                  .pathMatchers("/auth/**").permitAll()
                  .pathMatchers("/medical-records/**").permitAll()
                  .anyExchange().authenticated()
            )
            .build();
   }
}
