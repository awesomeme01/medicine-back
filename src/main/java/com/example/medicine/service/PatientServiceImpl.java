package com.example.medicine.service;

import com.example.medicine.helper.PatientUpdateWrapper;
import com.example.medicine.model.PatientCard;
import com.example.medicine.model.User;
import com.example.medicine.repository.PatientCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientCardRepository patientCardRepository;
    @Autowired
    UserService userService;

    @Override
    public PatientCard getById(Long id) {
        return patientCardRepository.findById(id).get();
    }

    @Override
    public List<PatientCard> getAll() {
        return patientCardRepository.findAll();
    }

    @Override
    public PatientCard create(PatientCard patientCard) {
        return patientCardRepository.save(patientCard);
    }

    @Override
    public void delete(Long id) {
        patientCardRepository.deleteById(id);
    }

    @Override
    public PatientCard update(PatientUpdateWrapper patientUpdateWrapper) {
        PatientCard p = patientCardRepository.findById(patientUpdateWrapper.getId()).get();

        if(patientUpdateWrapper.getAddress()!=null){
            p.setAddress(patientUpdateWrapper.getAddress());
            patientCardRepository.save(p);
        }
        return p;
    }

    @Override
    public List<User> getAllPatients() {
        return userService.getAllUsers().stream().filter(x->x.getPatientCard()!=null).collect(Collectors.toList());
    }
}
