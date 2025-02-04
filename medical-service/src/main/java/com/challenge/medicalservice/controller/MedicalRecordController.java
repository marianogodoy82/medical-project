package com.challenge.medicalservice.controller;

import com.challenge.medicalservice.model.MedicalRecord;
import com.challenge.medicalservice.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {
   private final MedicalRecordService service;

   @PostMapping
   @PreAuthorize("hasRole('USER')")
   public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
      return ResponseEntity.ok(service.createMedicalRecord(medicalRecord));
   }

   @GetMapping("/user/{email}")
   @PreAuthorize("hasRole('USER') or hasRole('DOCTOR') or hasRole('OPERATOR')")
   public ResponseEntity<List<MedicalRecord>> getByUser(@PathVariable String email) {
      return ResponseEntity.ok(service.getMedicalRecordsByUser(email));
   }

   @GetMapping("/doctor/{id}")
   public ResponseEntity<List<MedicalRecord>> getByDoctor(@PathVariable String id) {
      return ResponseEntity.ok(service.getMedicalRecordsByDoctor(id));
   }
}