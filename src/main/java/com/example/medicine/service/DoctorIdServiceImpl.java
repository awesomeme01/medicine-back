package com.example.medicine.service;

import com.example.medicine.model.DoctorId;
import com.example.medicine.model.User;
import com.example.medicine.repository.DoctorIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorIdServiceImpl implements DoctorIdService{
    @Autowired
    DoctorIdRepository doctorIdRepository;
    @Override
    public List<DoctorId> getAll() {
        return doctorIdRepository.findAll();
    }

    @Override
    public DoctorId create(DoctorId doctorId) {
        return doctorIdRepository.save(doctorId);
    }

    @Override
    public void delete(Long id) {
        doctorIdRepository.deleteById(id);
    }

    @Override
    public DoctorId getByUser(User user) {
        List<DoctorId> doctorIds = doctorIdRepository.findAll();
        for(DoctorId doctorId: doctorIds){
            if(doctorId.getUser().equals(user)){
                return  doctorId;
            }
        }

        return null;
    }
}
