package com.example.medicine.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "drug_med_1")
public class DrugName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receipt_id")
    private Receipt receiptId;

    public DrugName() {
    }

    public Receipt getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Receipt receiptId) {
        this.receiptId = receiptId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
