package com.example.medicine.service;

import com.example.medicine.model.Diagnose;
import com.example.medicine.model.Receipt;
import com.example.medicine.model.Reference;
import com.example.medicine.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenceServiceImpl implements ReferenceService{
    @Autowired
    ReferenceRepository referenceRepository;
    @Override
    public List<Reference> getAllByDiagnose(Diagnose diagnose) {
        return referenceRepository.findAll().stream().filter(x->x.getDiagnose().equals(diagnose)).collect(Collectors.toList());
    }

    @Override
    public Reference create(Reference receipt) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
