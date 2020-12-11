package com.example.medicine.model;

import javax.persistence.*;

@Entity
@Table(name = "doctorid_med_1")
public class DoctorId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "degree")
    private String degree;
    @ManyToOne
    @JoinColumn(name = "division")
    private Division division;
    @Column(name = "status")
    private int status;

    public DoctorId() {
        this.status = 1;
    }

    public DoctorId(Long id, String degree, Division division, int status) {
        this.id = id;
        this.degree = degree;
        this.division = division;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //TODO:need fields
}
