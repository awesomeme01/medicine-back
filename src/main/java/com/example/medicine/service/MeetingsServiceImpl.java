package com.example.medicine.service;

import com.example.medicine.model.Meeting;
import com.example.medicine.model.User;
import com.example.medicine.repository.MeetingsRepository;
import com.example.medicine.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingsServiceImpl implements MeetingsService{
    @Autowired
    MeetingsRepository meetingsRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public List<Meeting> getAll() {
        List<Meeting> meetings = meetingsRepository.findAll();
        for(Meeting meeting: meetings){
            meeting.setPayments(paymentRepository.findAll().stream().filter(x->x.getParent().equals(meeting)).collect(Collectors.toList()));
        }
        return meetings;
    }

    @Override
    public List<Meeting> getByPatient(User user) {
        List<Meeting> meetings = meetingsRepository.findAll().stream().filter(x->x.getUserPatient().equals(user)).collect(Collectors.toList());
        for(Meeting meeting: meetings){
            meeting.setPayments(paymentRepository.findAll().stream().filter(x->x.getParent().equals(meeting)).collect(Collectors.toList()));
        }
        return meetings;
    }

    @Override
    public List<Meeting> getByDoctor(User user) {
        List<Meeting> meetings = meetingsRepository.findAll().stream().filter(x->x.getUserDoctor().equals(user)).collect(Collectors.toList());
        for(Meeting meeting: meetings){
            meeting.setPayments(paymentRepository.findAll().stream().filter(x->x.getParent().equals(meeting)).collect(Collectors.toList()));
        }
        return meetings;
    }

    @Override
    public Meeting create(Meeting meeting) {
        return meetingsRepository.save(meeting);
    }

    @Override
    public void delete(Long id) {
        meetingsRepository.deleteById(id);
    }

    @Override
    public Meeting getById(Long id) {
        return meetingsRepository.findById(id).get();
    }

    @Override
    public Meeting changeStatus(Long id, int status) {
        Meeting meeting = meetingsRepository.findById(id).get();
        meeting.setStatus(status);//0 = arranged -1 canceled 1 accomplished
        return meetingsRepository.save(meeting);
    }
}
