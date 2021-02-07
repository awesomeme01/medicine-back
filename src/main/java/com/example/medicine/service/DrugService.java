package com.example.medicine.service;

import com.example.medicine.model.DrugName;
import com.example.medicine.model.Receipt;

import java.util.List;

public interface DrugService {
    List<DrugName> getByReceipt(Receipt receipt);
    DrugName create(DrugName drugName);
    void delete(Long id);
}
