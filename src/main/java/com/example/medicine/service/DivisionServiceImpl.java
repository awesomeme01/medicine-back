package com.example.medicine.service;

import com.example.medicine.model.Division;
import com.example.medicine.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DivisionServiceImpl implements DivisionService{
    @Autowired
    DivisionRepository divisionRepository;
    @Override
    public List<Division> getAll() {
        return divisionRepository.findAll();
    }

    @Override
    public Division create(Division division) {
        return divisionRepository.save(division);
    }

    @Override
    public void deleteById(Long id) {
        divisionRepository.deleteById(id);
    }
}
