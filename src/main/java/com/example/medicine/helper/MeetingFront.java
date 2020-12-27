package com.example.medicine.helper;

import com.example.medicine.model.Meeting;
import com.example.medicine.model.Payment;

import java.util.List;

public class MeetingFront {
    private Meeting meeting;
    private List<Payment> payments;

    public MeetingFront() {
    }

    public MeetingFront(Meeting meeting, List<Payment> payments) {
        this.meeting = meeting;
        this.payments = payments;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
