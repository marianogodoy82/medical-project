package com.challenge.authservice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.authservice.dto.SignupRequest;
import com.challenge.authservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;

   @PostMapping("/signup")
   public ResponseEntity<Map<String, String>> register(@RequestBody SignupRequest request) {
      String token = authService.register(
            request.email(),
            request.password(),
            request.firstName(),
            request.lastName(),
            request.role());
      return ResponseEntity.ok(Map.of("token", token));
   }

   @PostMapping("/login")
   public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
      String token = authService.login(request.get("email"), request.get("password"));
      return ResponseEntity.ok(Map.of("token", token));
   }
}
