package com.example.medicine.service;

import com.example.medicine.model.Diagnose;
import com.example.medicine.model.User;

import java.util.List;

public interface DiagnoseService {
    List<Diagnose> getByPatient(Long patientId);
    List<Diagnose> getByDoctor(Long doctorId);
    Diagnose createForUser(String doctorUsername,Long patientId, Diagnose diagnose);
    void deleteById(Long id);

}
