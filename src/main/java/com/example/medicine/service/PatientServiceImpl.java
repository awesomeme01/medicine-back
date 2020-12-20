package com.example.medicine.service;

import com.example.medicine.model.PatientCard;
import com.example.medicine.repository.PatientCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientCardRepository patientCardRepository;

    @Override
    public PatientCard getById(Long id) {
        return patientCardRepository.findById(id).get();
    }

    @Override
    public List<PatientCard> getAll() {
        return patientCardRepository.findAll();
    }

    @Override
    public PatientCard create(PatientCard patientCard) {
        return patientCardRepository.save(patientCard);
    }

    @Override
    public void delete(Long id) {
        patientCardRepository.deleteById(id);
    }
}
