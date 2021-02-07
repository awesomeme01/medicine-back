package com.example.medicine.service;

import com.example.medicine.model.DrugName;
import com.example.medicine.model.Receipt;
import com.example.medicine.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImpl implements DrugService{
    @Autowired
    DrugRepository drugRepository;

    @Override
    public List<DrugName> getByReceipt(Receipt receipt) {
        return drugRepository.findAll().stream().filter(x->x.getReceiptId().equals(receipt)).collect(Collectors.toList());
    }

    @Override
    public DrugName create(DrugName drugName) {
        return drugRepository.save(drugName);
    }

    @Override
    public void delete(Long id) {
        drugRepository.deleteById(id);
    }
}
