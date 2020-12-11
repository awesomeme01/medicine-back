package com.example.medicine.repository;

import com.example.medicine.model.DoctorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorIdRepository extends JpaRepository<DoctorId, Long> {
}
