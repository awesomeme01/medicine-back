package com.example.medicine.service;

import com.example.medicine.model.Diagnose;
import com.example.medicine.repository.DiagnoseRepository;
import com.example.medicine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnoseServiceImpl implements DiagnoseService{
    @Autowired
    DiagnoseRepository diagnoseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Diagnose> getByPatient(Long patientId) {
        return diagnoseRepository.findAll().stream().filter(x->x.getPatient().getId().equals(patientId)).collect(Collectors.toList());
    }

    @Override
    public List<Diagnose> getByDoctor(Long doctorId) {
        return diagnoseRepository.findAll().stream().filter(x->x.getDoctor().getId().equals(doctorId)).collect(Collectors.toList());
    }

    @Override
    public Diagnose createForUser(String doctorUsername,Long patientId, Diagnose diagnose) {
        diagnose.setDoctor(userRepository.findByUsername(doctorUsername));
        diagnose.setPatient(userRepository.findById(patientId).get());
        return diagnoseRepository.save(diagnose);
    }

    @Override
    public void deleteById(Long id) {
        diagnoseRepository.deleteById(id);
    }
}
