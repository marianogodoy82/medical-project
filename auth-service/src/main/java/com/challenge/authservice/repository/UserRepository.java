package com.challenge.authservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.authservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByEmail(String email);

}
