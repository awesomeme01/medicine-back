package com.example.medicine.model;

//
//import com.example.medicine.helper.Payable;

import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meeting_med_1")
public class Meeting{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userPatient")
    private User userPatient;
    @ManyToOne
    @JoinColumn(name = "userDoctor")
    private User userDoctor;
    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;
    @Column(name = "dateFrom")
    private LocalDateTime dateFrom;
    @Column(name = "dateTo")
    private LocalDateTime dateTo;
    @Column(name = "status")
    private int status;
    @Column(name = "amountToBePaid")
    private Double amountToBePaid;
    @Column(name = "statusPaid")
    private int statusPaid;
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User createdBy;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)

//    private List<Payment> payments;

    public Meeting() {
        this.status = 0;
        this.statusPaid = 0;
        this.amountToBePaid = (double)0;
    }

    public Meeting(Long id, User userPatient, User userDoctor, int status) {
        this.id = id;
        this.userPatient = userPatient;
        this.userDoctor = userDoctor;
        this.status = status;
    }

    public Double getAmountToBePaid() {
        return amountToBePaid;
    }

    public int getStatusPaid() {
        return statusPaid;
    }

    public void setStatusPaid(int statusPaid) {
        this.statusPaid = statusPaid;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void setAmountToBePaid(Double amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }


//    public Object getPayments() {
//        return payments;
//    }
//
//    public void setPayments(Object payments) {
//        this.payments = payments;
//    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
