package com.example.medicine.service;

import com.example.medicine.model.Address;
import com.example.medicine.model.User;

import java.util.List;

public interface AddressService {
    Address getById(Long id);
    List<Address> getAll();
    void delete(Long id);
    Address create(Address address);
}
