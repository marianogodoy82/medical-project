package com.challenge.authservice.exception;

public class EmailAlreadyExistException extends RuntimeException {

   public EmailAlreadyExistException(String message) {
      super(message);
   }
}
