package com.challenge.apigateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.challenge.apigateway.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class JwtAuthGlobalFilter implements GlobalFilter {

   private final JwtUtil jwtUtil;

   @Override
   public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
      log.debug("🔥 Entrando en JwtAuthGlobalFilter...");

      String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
         log.debug("❌ No se encontró Authorization Header en API Gateway");
         return chain.filter(exchange);
      }

      try {
         String token = authHeader.substring(7);
         String email = jwtUtil.extractEmail(token);
         final List<String> roles = jwtUtil.extractRoles(token);

         final List<SimpleGrantedAuthority> authorities = roles.stream()
                                                               .map(SimpleGrantedAuthority::new)
                                                               .toList();

         final SecurityContextImpl securityContext = new SecurityContextImpl(new UsernamePasswordAuthenticationToken(email, null, authorities));

         ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
               .header("X-User-Email", email)
               .header("X-User-Roles", String.join(",", roles))
               .build();

         return chain.filter(exchange.mutate().request(modifiedRequest).build())
               .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));

      } catch (Exception e) {
         return chain.filter(exchange);
      }
   }
}
