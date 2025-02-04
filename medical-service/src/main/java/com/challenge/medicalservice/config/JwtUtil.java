package com.challenge.medicalservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

   private final SecretKey signingKey;

   public JwtUtil(@Value("${jwt.secret}") String secret) {
      this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
   }

   @Value("${jwt.expiration}")
   private long expiration;

   public String generateToken(String username) {
      return Jwts.builder()
                 .subject(username)
                 .issuedAt(new Date())
                 .expiration(new Date(System.currentTimeMillis() + expiration))
                 .signWith(signingKey)
                 .compact();
   }

   public boolean validateToken(String token) {
      try {
         Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public String extractUsername(String token) {
      return Jwts.parser()
                 .verifyWith(signingKey)
                 .build()
                 .parseSignedClaims(token)
                 .getPayload()
                 .getSubject();
   }
}