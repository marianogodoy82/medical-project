package com.challenge.apigateway.util;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

   private static final String SECRET_KEY = "secretoMuySeguroParaFirmarJWTconUnaClaveMasLarga";
   private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

   public boolean validateToken(String token) {
      try {
         Jwts.parser()
             .verifyWith(key)
             .build()
             .parseSignedClaims(token);
         return true;
      } catch (JwtException e) {
         return false;
      }
   }

   public Claims extractClaims(String token) {
      return Jwts.parser()
                 .verifyWith(key)
                 .build()
                 .parseSignedClaims(token)
                 .getPayload();
   }
}