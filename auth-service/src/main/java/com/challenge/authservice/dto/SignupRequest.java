package com.challenge.authservice.dto;

public record SignupRequest(
   String email,
   String password,
   String firstName,
   String lastName
) { }
