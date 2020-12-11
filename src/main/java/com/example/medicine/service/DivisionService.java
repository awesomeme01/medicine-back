package com.example.medicine.service;


import com.example.medicine.model.Division;

import java.util.List;

public interface DivisionService {
    List<Division> getAll();
    Division create(Division division);
    void deleteById(Long id);
}
