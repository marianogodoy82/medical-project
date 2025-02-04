package com.challenge.authservice.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;

@Builder
@Document(collection = "users")
public record User(
      @Id
      String id,
      String email,
      String password,
      String firstName,
      String lastName,
      String birthDate,
      String address,
      Set<String> roles
) {}
