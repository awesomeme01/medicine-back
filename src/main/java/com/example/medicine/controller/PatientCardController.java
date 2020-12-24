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
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

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
            return new Response(true, "Все зарегистрированные карты пациетов","All Patient Cards", patientService.getAll());
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
            patientCard.setPatient(userService.createUser(patient));
            userRoleService.createUserRole(new UserRole("ROLE_PATIENT", patient));
            patientCard.setAddress(addressService.create(patientCardWrapper.getAddress()));
            patientCard.setCreatedBy(userService.getByUsername(principal.getName()));
            patientService.create(patientCard);
            patient.setPatientCardId(patientCard.getId());
            userService.updateUser(patient);
            return new Response(true, "Создан новый пациент и его медкарта","New Patient Object Created!", patientCard);
        }
        catch (ConstraintViolationException ex){
            return new Response(false, "Нарушение правил по уникальности. Такой пациент или такая медкарта уже существует", "Constraints of uniqueness were violated! Such patient or medcard already exists", ex.getMessage());
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
        catch (NoSuchElementException ex){
            return new Response(false, "Не существует такого объекта с ID номером " + patientUpdateWrapper.getId()+". Ошибка: " + ex.getMessage(),"No such object with id = " + patientUpdateWrapper.getId() + " Error: " + ex.getMessage(), ex.getStackTrace());
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_PATIENT")
    @GetMapping("/getMyCard")
    public Response getMyPatientCard(Principal principal){
        try{
            PatientCard p = patientService.getById(userService.getByUsername(principal.getName()).getPatientCardId());
            p.setCreatedBy(null);
            return new Response(true, "Моя медкарта","PatientCard Object For Patient", p);
        }
        catch (InvalidDataAccessApiUsageException ex){
            return new Response(false, "Ошибка, нынешний пользователь не зарегистрирован как пациент", "Current user is not registered as Patient", ex.getCause(),null);
        }
        catch (Exception ex){
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
        catch (NoSuchElementException ex){
            return new Response(false, "Не существует карты которую вы пытаетесь удалить!","No such card with the given id: " + ex.getMessage(), ex.getStackTrace());
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }

    }

}
