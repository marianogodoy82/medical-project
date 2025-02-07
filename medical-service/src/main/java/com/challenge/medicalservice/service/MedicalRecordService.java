package com.challenge.medicalservice.service;

import com.challenge.medicalservice.model.MedicalRecord;
import com.challenge.medicalservice.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {
   private final MedicalRecordRepository repository;

   public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
      return repository.save(medicalRecord);
   }

   public List<MedicalRecord> getMedicalRecordsByUser(String userEmail, String role) {
      return switch (role) {
         case "USER" -> repository.findByUserEmail(userEmail);
         case "DOCTOR" -> repository.findByDoctorId(userEmail);
         case "OPERATOR" -> repository.findAll();
         default-> throw new RuntimeException("Invalid role");
      };
   }
}