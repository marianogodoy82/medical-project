package com.challenge.apigateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

   @Bean
   public OpenAPI customOpenAPI() {
      return new OpenAPI()
            .info(new Info()
                  .title("API Gateway - Microservices Documentation")
                  .version("1.0.0")
                  .description("Esta documentación centraliza las APIs de Auth y Medical Service"))
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new io.swagger.v3.oas.models.Components()
                  .addSecuritySchemes("Bearer Authentication",
                        new SecurityScheme()
                              .name("Authorization")
                              .type(SecurityScheme.Type.HTTP)
                              .scheme("bearer")
                              .bearerFormat("JWT")));
   }
}