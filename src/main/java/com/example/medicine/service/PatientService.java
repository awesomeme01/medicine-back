package com.example.medicine.service;

import com.example.medicine.model.PatientCard;

import java.util.List;

public interface PatientService {
    PatientCard getById(Long id);
    List<PatientCard> getAll();
    PatientCard create(PatientCard patientCard);
    void delete(Long id);
}
