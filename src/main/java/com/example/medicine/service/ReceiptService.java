package com.example.medicine.service;

import com.example.medicine.model.Diagnose;
import com.example.medicine.model.Receipt;

import java.util.List;

public interface ReceiptService {
    List<Receipt> getAllByDiagnose(Diagnose diagnose);
    Receipt create(Receipt receipt);
    void delete(Long id);
}
