package com.example.medicine.service;

import com.example.medicine.model.Diagnose;
import com.example.medicine.model.Reference;

import java.util.List;

public interface ReferenceService {
    List<Reference> getAllByDiagnose(Diagnose diagnose);
    Reference create(Reference receipt);
    void delete(Long id);
}
