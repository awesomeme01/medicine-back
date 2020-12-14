package com.example.medicine.model;

import com.example.medicine.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "user_med_1")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "email", nullable = false, unique = true, length = 70)
    private String email;
    @Column(name = "fullname", nullable = false, unique = true, length = 80)
    private String fullname;
    @Column(name = "phone_number", length = 14, unique = true)
    private String phoneNumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "is_active",nullable = false)
    private int isActive;
    @Column(name = "birthDate", nullable = false)
    private LocalDateTime birthDate;
    @Column(name = "cardNumber")
    private Long cardNumber;
    private ArrayList<String> roles;


    private User() {
        this.isActive = 1;
    }

    public static class Builder{
        private String username;
        private String email;
        private String password;
        private LocalDateTime birthDate;
        private String fullname;
        private Gender gender;
        private int isActive;
        private String phoneNumber;

        public Builder(String username){
            this.username = username;
            this.isActive = 1;
        }
        public Builder withPhonenumber(String phonenumber){
            this.phoneNumber = phonenumber;
            return this;
        }
        public Builder withFullname(String fullname){
            this.fullname = fullname;
            return this;
        }
        public Builder withDateOfBirth(LocalDateTime dateOfBirth){
            this.birthDate = dateOfBirth;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withPassword(String password){
            this.password = password;
            return this;
        }

        public Builder withGender(Gender gender){
            this.gender = gender;
            return this;
        }

        public Builder isActive(int isActive){
            this.isActive = isActive;
            return this;
        }

        public User build(){
            User user1 = new User();
            user1.username = this.username;
            user1.email = this.email;
            user1.fullname = this.fullname;
            user1.password = this.password;
            user1.gender = this.gender;
            user1.birthDate = this.birthDate;
            user1.isActive = this.isActive;
            user1.phoneNumber = this.phoneNumber;
            return user1;
        }

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
    public void addRoles(String role){
        this.roles.add(role);
    }
}
