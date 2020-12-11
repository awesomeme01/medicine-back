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

@RestController
@CrossOrigin("*")
@Secured("ROLE_USER")
@RequestMapping("/meeting")
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
    @Secured("ROLE_DOCTOR")
    @PostMapping("/create/{patientId}")
    public Response create(@PathVariable Long patientId, @RequestBody Meeting meeting, Principal principal){
        User user = userService.getByUsername(principal.getName());
        if(doctorIdService.getByUser(user).getStatus()!=1){
            return new Response(false, "Meeting cannot be created by non active doctor", null);
        }
        meeting.setUserDoctor(user);
        meeting.setUserPatient(userService.getUserById(patientId));
        return new Response(true, "Meeting for patient with id = " + patientId + " was created!", meetingsService.create(meeting));
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{meetingId}")
    public Response delete(@PathVariable Long meetingId){
        meetingsService.delete(meetingId);
        return new Response(true, "Meeting with id = " + meetingId + " was deleted!", null);
    }
}
