package com.challenge.apigateway.filter;

import io.jsonwebtoken.Claims;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.challenge.apigateway.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements WebFilter {

   private final JwtUtil jwtUtil;

   @Override
   public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
      ServerHttpRequest request = exchange.getRequest();

      // Verificar si la ruta es pública
      if (request.getURI().getPath().contains("/auth/")) {
         return chain.filter(exchange);
      }

      // Obtener el token JWT de los headers
      String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
         throw new RuntimeException("Token no proporcionado");
      }

      String token = authHeader.substring(7);
      if (!jwtUtil.validateToken(token)) {
         throw new RuntimeException("Token inválido");
      }

      Claims claims = jwtUtil.extractClaims(token);
      request = request.mutate()
                       .header("X-User-Email", claims.getSubject())
                       .build();

      return chain.filter(exchange.mutate().request(request).build());
   }
}