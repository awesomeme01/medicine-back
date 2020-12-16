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
@Secured("ROLE_USER")
@RequestMapping("api/meeting")
public class MeetingController {
    @Autowired
    MeetingsService meetingsService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorIdService doctorIdService;
    @GetMapping("/getAll")
    public Response getAll(){
        return new Response(true, "All meeting objects", meetingsService.getAll());
    }
    @GetMapping("/getFrom")
    public Response getFrom(@RequestBody LocalDateTime time){
        return new Response(true, "Meeting calendar from today", meetingsService.getAll().stream().filter(x->x.getDateFrom().plusHours(6).isAfter(time)));
    }
    @Secured("ROLE_RECEPTION")
    @PostMapping("/create/{patientId}")
    public Response create(@PathVariable Long patientId, @RequestBody Meeting meeting, Principal principal){
        meeting.setUserPatient(userService.getUserById(patientId));
        return new Response(true, "Meeting for patient with id = " + patientId + " was created!", meetingsService.create(meeting));
    }
    @Secured("ROLE_PATIENT")
    @PostMapping("/meetingHappened/{id}")
    public Response meetingHappened(@PathVariable Long id, Principal principal){
        User user = userService.getByUsername(principal.getName());
        if(meetingsService.getById(id).getUserPatient().equals(user)){
            return new Response(true, "Meeting happened with id = " + id, meetingsService.changeStatus(id, 1));
        }
        return new Response(false, "Meeting doesn't belong to the user connected", null);
    }
    @Secured("ROLE_PATIENT")
    @PostMapping("/cancelMeeting/{id}")
    public Response cancelMeeting(@PathVariable Long id, Principal principal){
        User user = userService.getByUsername(principal.getName());
        if(meetingsService.getById(id).getUserPatient().equals(user)){
            return new Response(true, "Meeting cancelled with id = " + id, meetingsService.changeStatus(id, -1));
        }
        return new Response(false, "Meeting doesn't belong to the user connected", null);
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{meetingId}")
    public Response delete(@PathVariable Long meetingId){
        meetingsService.delete(meetingId);
        return new Response(true, "Meeting with id = " + meetingId + " was deleted!", null);
    }
}
