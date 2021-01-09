package com.example.medicine.repository;

import com.example.medicine.model.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
}
