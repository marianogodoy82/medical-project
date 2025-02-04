package com.challenge.medicalservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
      info = @Info(
            title = "Medical API",
            version = "1.0.0",
            description = "This is a API for Medical Operations"
      )
)
public class OpenApiConfig {

}
