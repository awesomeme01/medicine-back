package com.example.medicine.helper;

import com.example.medicine.model.Address;
import com.example.medicine.model.PatientCard;

public class PatientCardWrapper {
    private PatientCard patientCard;
    private Address address;
    private Long doctorId;

    public PatientCardWrapper() {
    }

    public PatientCardWrapper(PatientCard patientCard, Address address, Long doctorId) {
        this.patientCard = patientCard;
        this.address = address;
        this.doctorId = doctorId;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
