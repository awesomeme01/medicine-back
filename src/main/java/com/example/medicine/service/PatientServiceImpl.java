package com.example.medicine.service;

import com.example.medicine.helper.PatientUpdateWrapper;
import com.example.medicine.model.Address;
import com.example.medicine.model.PatientCard;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
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
    @Autowired
    AddressService addressService;
    @Autowired
    UserRoleService userRoleService;
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
        PatientCard p = patientCardRepository.findById(id).get();
        User user = p.getPatient();
        user.setPatientCardId(null);
        for(UserRole u :userRoleService.getUserRolesByUser(user)){
            if(u.getRole().equals("ROLE_PATIENT")){
                userRoleService.deleteUserRoles(u.getId());
            }
        }
        userService.updateUser(user);
        addressService.delete(p.getAddress().getId());//patient card automatically deletes itself
    }

    @Override
    public PatientCard update(PatientUpdateWrapper patientUpdateWrapper) {
        PatientCard p = patientCardRepository.findById(patientUpdateWrapper.getId()).get();
        if(patientUpdateWrapper.getAddress()!=null){
            Address a = patientUpdateWrapper.getAddress();
            a.setId(p.getAddress().getId());
            p.setAddress(addressService.create(a));
        }
        return p;
    }

    @Override
    public List<User> getAllPatients() {
        return userService.getAllUsers().stream().filter(x->x.getPatientCardId()!=null).collect(Collectors.toList());
    }
}
