package com.example.medicine.model;

import javax.persistence.*;

@Entity
@Table(name = "address_med_1")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String district;
    @Column
    private String city;
    @Column
    private String inhabitedLocality;
    @Column
    private String street;
    @Column
    private String house;
    @Column
    private String apartment;
    @Column
    private String homeTelephone;

    public Address() {
    }

    public Address(String district, String city, String inhabitedLocality, String street, String house, String apartment, String homeTelephone) {
        this.district = district;
        this.city = city;
        this.inhabitedLocality = inhabitedLocality;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.homeTelephone = homeTelephone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInhabitedLocality() {
        return inhabitedLocality;
    }

    public void setInhabitedLocality(String inhabitedLocality) {
        this.inhabitedLocality = inhabitedLocality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }
}
