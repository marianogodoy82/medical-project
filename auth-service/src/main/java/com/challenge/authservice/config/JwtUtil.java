package com.challenge.authservice.config;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.challenge.authservice.model.User;

@Component
public class JwtUtil {

   private final SecretKey signingKey;

   @Value("${jwt.expiration}")
   private long expiration;

   public JwtUtil(@Value("${jwt.secret}") String secret) {
      this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
   }

   public String generateToken(User user) {
      return Jwts.builder()
                 .subject(user.email())
                 .claim("roles", user.roles())
                 .issuedAt(new Date())
                 .expiration(new Date(System.currentTimeMillis() + expiration))
                 .signWith(signingKey)
                 .compact();
   }

   public String extractEmail(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   public boolean validateToken(String token, String userEmail) {
      return extractEmail(token).equals(userEmail) && !isTokenExpired(token);
   }

   private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      Claims claims = Jwts.parser()
                          .verifyWith(signingKey)
                          .build()
                          .parseSignedClaims(token)
                          .getPayload();
      return claimsResolver.apply(claims);
   }
}
