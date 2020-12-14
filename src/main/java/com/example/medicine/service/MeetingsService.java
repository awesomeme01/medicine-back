package com.example.medicine.service;

import com.example.medicine.model.Meeting;
import com.example.medicine.model.User;

import java.util.List;

public interface MeetingsService {
    List<Meeting> getAll();
    List<Meeting> getByPatient(User user);
    List<Meeting> getByDoctor(User user);
    Meeting changeStatus(Long id, int status);
    Meeting getById(Long id);
    Meeting create(Meeting meeting);
    void delete(Long id);
}
