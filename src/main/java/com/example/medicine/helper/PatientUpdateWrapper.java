package com.example.medicine.helper;

import com.example.medicine.model.Address;

public class PatientUpdateWrapper {

    private Long id;
    private Address address;

    //TODO:add new as we expand the patient card fields

    public PatientUpdateWrapper() {
    }

    public PatientUpdateWrapper(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
