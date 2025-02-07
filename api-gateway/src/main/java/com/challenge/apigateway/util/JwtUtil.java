package com.challenge.apigateway.util;

import java.util.List;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

   @Value("${jwt.secret}")
   private String secretKey;

   private SecretKey getSigningKey() {
      byte[] keyBytes = Decoders.BASE64.decode(secretKey);
      return Keys.hmacShaKeyFor(keyBytes);
   }

   public Claims extractClaims(String token) {
      return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
   }

   public String extractEmail(String token) {
      return extractClaims(token).getSubject();
   }

   public List<String> extractRoles(String token) {
      return extractClaims(token).get("roles", List.class);
   }
}