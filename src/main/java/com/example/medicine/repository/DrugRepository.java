package com.example.medicine.repository;

import com.example.medicine.model.DrugName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<DrugName, Long> {
}
