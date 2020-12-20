package com.example.medicine.repository;

import com.example.medicine.model.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientCardRepository extends JpaRepository<PatientCard, Long> {
}
