package com.challenge.medicalservice.repository;

import com.challenge.medicalservice.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
   List<MedicalRecord> findByUserEmail(String userEmail);
   List<MedicalRecord> findByDoctorId(String doctorId);
}
