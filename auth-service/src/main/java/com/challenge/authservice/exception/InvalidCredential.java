package com.challenge.authservice.exception;

public class InvalidCredential extends RuntimeException {

   public InvalidCredential(String message) {
      super(message);
   }
}
