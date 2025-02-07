package com.challenge.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.challenge.apigateway.exception.GlobalExceptionHandler;

@SpringBootApplication(scanBasePackages = "com.challenge.apigateway")
public class ApiGatewayApplication {

   public static void main(String[] args) {
      SpringApplication.run(ApiGatewayApplication.class, args);
   }

   @Bean
   public GlobalExceptionHandler globalExceptionHandler() {
      return new GlobalExceptionHandler();
   }
}
