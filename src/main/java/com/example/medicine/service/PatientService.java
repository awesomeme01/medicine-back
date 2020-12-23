package com.example.medicine.service;

import com.example.medicine.helper.PatientUpdateWrapper;
import com.example.medicine.model.PatientCard;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;

import java.util.List;

public interface PatientService {
    PatientCard getById(Long id);
    List<PatientCard> getAll();
    PatientCard create(PatientCard patientCard);
    void delete(Long id);
    List<User> getAllPatients();
    PatientCard update(PatientUpdateWrapper patientUpdateWrapper);
}
