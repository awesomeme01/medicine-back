package com.example.medicine.service;

import com.example.medicine.model.DoctorId;
import com.example.medicine.model.User;

import java.util.List;

public interface DoctorIdService {
    List<DoctorId> getAll();
    DoctorId create(DoctorId doctorId);
    void delete(Long id);
    DoctorId getByUser(User user);
}
