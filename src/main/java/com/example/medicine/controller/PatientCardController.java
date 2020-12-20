package com.example.medicine.controller;


import com.example.medicine.helper.PatientCardWrapper;
import com.example.medicine.helper.Response;
import com.example.medicine.model.PatientCard;
import com.example.medicine.model.User;
import com.example.medicine.service.AddressService;
import com.example.medicine.service.PatientService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/patientController")
public class PatientCardController {
    @Autowired
    PatientService patientService;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Secured("ROLE_DOCTOR")
    @GetMapping("/getAll")
    public Response getAll(Principal principal){
        return new Response(true, "All Patient of the current doctor", patientService.getAll().stream().filter(x->x.getDoctor().equals(userService.getByUsername(principal.getName()))).collect(Collectors.toList()));
    }

    @Secured("ROLE_RECEPTION")
    @PostMapping("/create")
    public Response createPatient(@RequestBody PatientCardWrapper patientCardWrapper, Principal principal){
        PatientCard patientCard = patientCardWrapper.getPatientCard();
        User doctor = userService.getUserById(patientCardWrapper.getDoctorId());
        patientCard.setDoctor(doctor);
        patientCard.setAddress(addressService.create(patientCardWrapper.getAddress()));
        patientCard.setCreatedBy(userService.getByUsername(principal.getName()));
        return new Response(true, "New Patient Object Created!", patientService.create(patientCard));
    }

    @Secured("ROLE_PATIENT")
    @GetMapping("/getMyCard")
    public Response getMyPatientCard(Principal principal){
        return new Response(true, "Patient Object For Patient", userService.getByUsername(principal.getName()).getPatientCard());
    }
    @Secured("ROLE_RECEPTION")
    @DeleteMapping("/deletePatientsCard/{id}")
    public Response delete(@PathVariable Long id){
        patientService.delete(id);
        return new Response(true, "Patient's card was deleted!", null);
    }

}
