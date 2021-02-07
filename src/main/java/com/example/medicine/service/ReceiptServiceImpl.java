package com.example.medicine.service;

import com.example.medicine.model.Diagnose;
import com.example.medicine.model.Receipt;
import com.example.medicine.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    ReceiptRepository receiptRepository;

    @Override
    public List<Receipt> getAllByDiagnose(Diagnose diagnose) {
        return receiptRepository.findAll().stream().filter(x->x.getDiagnose().equals(diagnose)).collect(Collectors.toList());
    }

    @Override
    public Receipt create(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public void delete(Long id) {
        receiptRepository.deleteById(id);
    }
}
