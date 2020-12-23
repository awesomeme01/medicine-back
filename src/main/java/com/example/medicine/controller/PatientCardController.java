package com.example.medicine.controller;


import com.example.medicine.helper.PatientCardWrapper;
import com.example.medicine.helper.PatientUpdateWrapper;
import com.example.medicine.helper.Response;
import com.example.medicine.model.PatientCard;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.service.AddressService;
import com.example.medicine.service.PatientService;
import com.example.medicine.service.UserRoleService;
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
    @Autowired
    UserRoleService userRoleService;
    @Secured("ROLE_RECEPTION")
    @GetMapping("/getAll")
    public Response getAll(){
        try{
            return new Response(true, "Все зарегистрированные пациенты","All Patients", patientService.getAll());
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }

    @Secured("ROLE_RECEPTION")
    @PostMapping("/create")
    public Response createPatient(@RequestBody PatientCardWrapper patientCardWrapper, Principal principal){
        try{
            PatientCard patientCard = patientCardWrapper.getPatientCard();
            User patient = patientCardWrapper.getPatient();
//        String fullnameSplit[] = patient.getFullname().toLowerCase().split(" ");
            patient.setUsername(patient.getPhoneNumber());
            patient.setPassword(""+patient.getBirthDate().getYear()+patient.getFullname().toLowerCase());//TODO: need to send randomly generated password via email or SMS
            userService.createUser(patient);
            userRoleService.createUserRole(new UserRole("ROLE_PATIENT", patient));
            patientCard.setPatient(patient);
            patientCard.setAddress(addressService.create(patientCardWrapper.getAddress()));
            patientCard.setCreatedBy(userService.getByUsername(principal.getName()));
            patientService.create(patientCard);
            patient.setPatientCard(patientCard);

            return new Response(true, "Создан новый пациент и его медкарта","New Patient Object Created!", patientCard);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }

    @Secured("ROLE_DOCTOR")
    @PostMapping("/updatePatientCard")
    public Response updatePatientCard(@RequestBody PatientUpdateWrapper patientUpdateWrapper){
        try{
            return new Response(true, "Обновлена карта пациента", "Patient card has been updated", patientService.update(patientUpdateWrapper));
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_PATIENT")
    @GetMapping("/getMyCard")
    public Response getMyPatientCard(Principal principal){
        try{
            return new Response(true, "Моя медкарта","PatientCard Object For Patient", userService.getByUsername(principal.getName()).getPatientCard());
        }catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_RECEPTION")
    @DeleteMapping("/deletePatientsCard/{id}")
    public Response delete(@PathVariable Long id){
        try{
            patientService.delete(id);
            return new Response(true, "Карта пациента была удалена","Patient's card was deleted!", null, null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }

}
