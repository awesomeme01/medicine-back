package com.example.medicine.repository;

import com.example.medicine.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingsRepository extends JpaRepository<Meeting,Long> {
}
