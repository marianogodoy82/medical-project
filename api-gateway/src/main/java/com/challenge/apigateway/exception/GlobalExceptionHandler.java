package com.challenge.apigateway.exception;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

   @Override
   public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
      log.error("⚠️ Error en API Gateway: {}", ex.getMessage(), ex);

      HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
      if (ex instanceof JwtAuthException) {
         status = HttpStatus.UNAUTHORIZED;
      }

      String jsonResponse = STR."""
            {
               "status":  "\{status.value()}",
               "error": "\{status.getReasonPhrase()}",
               "message": "\{ex.getMessage()}",
               "path": "\{exchange.getRequest().getPath().value()}"
            }
            """;

      exchange.getResponse().setStatusCode(status);
      exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
      DataBuffer buffer = exchange.getResponse()
                                  .bufferFactory()
                                  .wrap(jsonResponse.getBytes(StandardCharsets.UTF_8));

      return exchange.getResponse().writeWith(Mono.just(buffer));
   }
}
