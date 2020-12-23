package com.example.medicine.helper;

import com.example.medicine.model.Address;
import com.example.medicine.model.PatientCard;
import com.example.medicine.model.User;

public class PatientCardWrapper {
    private User patient;
    private PatientCard patientCard;
    private Address address;

    public PatientCardWrapper() {
        patientCard = new PatientCard();
    }

    public PatientCardWrapper(PatientCard patientCard, Address address, Long doctorId) {
        this.patientCard = patientCard;
        this.address = address;
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(PatientCard patientCard) {
        this.patientCard = patientCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }
}
