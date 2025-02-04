package com.challenge.authservice.exception;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

   @ExceptionHandler(EmailAlreadyExistException.class)
   public ResponseEntity<Map<String, Object>> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("timestamp", LocalDateTime.now());
      errorResponse.put("message", ex.getMessage());
      errorResponse.put("status", NOT_ACCEPTABLE.value());
      errorResponse.put("error", NOT_ACCEPTABLE.getReasonPhrase());
      return new ResponseEntity<>(errorResponse, NOT_ACCEPTABLE);
   }

   @ExceptionHandler(InvalidCredential.class)
   public ResponseEntity<Map<String, Object>> handleInvalidCredential(InvalidCredential ex) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("timestamp", LocalDateTime.now());
      errorResponse.put("message", ex.getMessage());
      errorResponse.put("status", UNAUTHORIZED.value());
      errorResponse.put("error", UNAUTHORIZED.getReasonPhrase());
      return new ResponseEntity<>(errorResponse, UNAUTHORIZED);
   }
}
