package com.challenge.authservice.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.authservice.config.JwtUtil;
import com.challenge.authservice.exception.EmailAlreadyExistException;
import com.challenge.authservice.exception.InvalidCredential;
import com.challenge.authservice.model.User;
import com.challenge.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
   private final UserRepository userRepository;
   private final JwtUtil jwtUtil;
   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   public String register(String email, String password, String firstName, String lastName, String role) {
      userRepository.findByEmail(email)
                    .ifPresent(user -> {
                       throw new EmailAlreadyExistException(STR."El email \{user} ya está en uso");
                    });

      final User user = User.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .firstName(firstName)
            .lastName(lastName)
            .roles(new HashSet<>(Set.of(role)))
            .build();
      userRepository.save(user);
      return jwtUtil.generateToken(user);
   }

   public String login(String email, String password) {
      final User user = userRepository
            .findByEmail(email)
            .filter(u -> passwordEncoder.matches(password, u.password()))
            .orElseThrow(() -> new InvalidCredential("Credenciales inválidas"));

      return jwtUtil.generateToken(user);
   }
}
