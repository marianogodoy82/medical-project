package com.challenge.medicalservice.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;

@Builder
@Document(collection = "medical_records")
public record MedicalRecord(
      @Id
      String id,
      String userEmail,
      String doctorId,
      String diagnosis,
      String treatment,
      LocalDateTime createdAt
) { }