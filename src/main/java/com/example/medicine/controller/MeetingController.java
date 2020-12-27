package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.model.Meeting;
import com.example.medicine.model.User;
import com.example.medicine.service.DoctorIdService;
import com.example.medicine.service.MeetingsService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@CrossOrigin("*")
@RequestMapping("api/meeting")
public class MeetingController {
    @Autowired
    MeetingsService meetingsService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorIdService doctorIdService;

    @Secured("ROLE_RECEPTION")
    @GetMapping("/getAll")
    public Response getAll() {
        try {
            return new Response(true, "Все встречи в базе данных", "All meeting objects", meetingsService.getAll());
        } catch (Exception ex) {
            return new Response(false, "Непредвиденная ошибка на сервере", "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_RECEPTION")
    @GetMapping("/getFrom")
    public Response getFrom(@RequestBody LocalDateTime time){
        try{
            return new Response(true, "Данные о записях от определенной даты","Meeting calendar from given date", meetingsService.getAll().stream().filter(x->x.getDateFrom().plusHours(6).isAfter(time)));
        }catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_RECEPTION")
    @PostMapping("/create/{patientId}/{doctorId}")
    public Response create(@PathVariable Long patientId, @PathVariable Long doctorId, @RequestBody Meeting meeting, Principal principal){
        try{
            meeting.setUserDoctor(userService.getUserById(doctorId));
            meeting.setUserPatient(userService.getUserById(patientId));
            if(meeting.getUserDoctor() == null || meeting.getUserPatient() == null){
                return new Response(false, "Введены некоректные данные о пациенте или враче", "Incorrect id for doctor or patient was entered", null, null);
            }
            meeting.setCreatedBy(userService.getByUsername(principal.getName()));
            return new Response(true, "Запись для пациента с ID " + patientId +" была создана.", "Meeting for patient with id = " + patientId + " was created!", meetingsService.create(meeting));
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }

    @Secured("ROLE_DOCTOR")
    @PostMapping("/meetingHappened/{id}")
    public Response meetingHappened(@PathVariable Long id, Principal principal){
        try{
            User user = userService.getByUsername(principal.getName());
            if(meetingsService.getById(id).getUserDoctor().equals(user)){
                return new Response(true, "Статус встречи изменен на ПРОИЗОШЕЛ","Meeting happened with id = " + id, meetingsService.changeStatus(id, 1));
            }
            return new Response(false, "Данная встреча не принадлежит ПОЛЬЗОВАТЕЛЮ - ВРАЧУ","Meeting doesn't belong to the user connected", null, null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage());
        }
    }
    @Secured({"ROLE_RECEPTION","ROLE_DOCTOR"})
    @PostMapping("/cancelMeeting/{id}")
    public Response cancelMeeting(@PathVariable Long id, Principal principal){
        try{
            User user = userService.getByUsername(principal.getName());
            if(meetingsService.getById(id).getUserPatient().equals(user) || meetingsService.getById(id).getCreatedBy().equals(user)){
                return new Response(true, "Встреча с ID номером " + id + " отменена","Meeting cancelled with id = " + id, meetingsService.changeStatus(id, -1));
            }
            return new Response(false, "Данная встреча не принадлежит ПОЛЬЗОВАТЕЛЮ", "Meeting doesn't belong to the user connected", null, null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{meetingId}")
    public Response delete(@PathVariable Long meetingId){
        try{
            meetingsService.delete(meetingId);
            return new Response(true, "Meeting with id = " + meetingId + " was deleted!", null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
}
