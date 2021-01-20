package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.model.Diagnose;
import com.example.medicine.service.DiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/diagnose")
public class DiagnoseController{
    @Autowired
    DiagnoseService diagnoseService;
    @Secured("ROLE_DOCTOR")
    @PostMapping("/createForUser/{patientId}")
    public Response create(@RequestBody Diagnose diagnose, @PathVariable Long patientId, Principal principal){
        try{
            return new Response(true, "Сохранен новый диагноз для пациента с ID номером = " + patientId, "Created diagnose for patient with ID = " + patientId, diagnoseService.createForUser(principal.getName(), patientId, diagnose));
        }catch (Exception ex) {
            return new Response(false, "Непредвиденная ошибка на сервере", "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/getByPatient/{id}")
    public Response getByPatient(@PathVariable Long id){
        try {
            return new Response(true, "История болезней пациента с id = " + id, "Diagnose history for patient with id = " + id, diagnoseService.getByPatient(id));
        }catch (Exception ex) {
            return new Response(false, "Непредвиденная ошибка на сервере", "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    
    @Secured({"ROLE_ADMIN","ROLE_DOCTOR"})
    @GetMapping("/getByDoctor/{id}")
    public Response getByDoctor(@PathVariable Long id){
        try{
            return new Response(true, "Все диагнозы созданные доктором с id = " + id, "Diagnoses created by doctor with id = " + id , diagnoseService.getByDoctor(id));
        }
        catch (Exception ex) {
            return new Response(false, "Непредвиденная ошибка на сервере", "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/deleteById/{id}")
    public Response deleteById(@PathVariable Long id){
        try{
            diagnoseService.deleteById(id);
            return new Response(true, "Удален диагноз с id = " + id, "Diagnose was deleted with id = " + id , null,null);
        }
        catch (Exception ex) {
            return new Response(false, "Непредвиденная ошибка на сервере", "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }



}
