package com.challenge.medicalservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.medicalservice.model.MedicalRecord;
import com.challenge.medicalservice.service.MedicalRecordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/medical-records")
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordController {
   private final MedicalRecordService service;

   @PostMapping
   public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
      return ResponseEntity.ok(service.createMedicalRecord(medicalRecord));
   }

   @GetMapping("/user/{email}")
   public ResponseEntity<List<MedicalRecord>> getByUser(
         @PathVariable("email") String email,
         @RequestHeader("X-User-Email") String userEmail,
         @RequestHeader("X-User-Roles") String userRoles
         ) {

      var roles = Set.of("DOCTOR", "OPERATOR", "USER");

      if ( !userEmail.equals(email) && !roles.contains(userRoles) ) {
         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
      }

      return ResponseEntity.ok(service.getMedicalRecordsByUser(email, userRoles));
   }

}
