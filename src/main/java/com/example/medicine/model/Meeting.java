package com.example.medicine.model;

import jdk.vm.ci.meta.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_med_1")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userPatient")
    private User userPatient;
    @ManyToOne
    @JoinColumn(name = "userDoctor")
    private User userDoctor;
    @Column(name = "dateTime")
    private LocalDateTime dateTime;
    @Column(name = "status")
    private int status;//0 = arranged -1 canceled 1 accomplished

    public Meeting() {
        this.status = 0;
        this.dateTime = LocalDateTime.now().plusHours(6);
    }

    public Meeting(Long id, User userPatient, User userDoctor, LocalDateTime dateTime, int status) {
        this.id = id;
        this.userPatient = userPatient;
        this.userDoctor = userDoctor;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserPatient() {
        return userPatient;
    }

    public void setUserPatient(User userPatient) {
        this.userPatient = userPatient;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
