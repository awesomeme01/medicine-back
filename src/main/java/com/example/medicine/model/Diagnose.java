package com.example.medicine.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnose_history")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "patientId")
    private User patient;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private User doctor;

    public Diagnose() {
        this.dateTime = LocalDateTime.now().plusHours(6);
    }

    public Diagnose(Long id, String title, LocalDateTime dateTime, User patient) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }
}
