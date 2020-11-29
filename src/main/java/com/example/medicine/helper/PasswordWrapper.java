package com.example.medicine.helper;

import com.example.medicine.model.User;

public class PasswordWrapper {
    private User user;
    private String newPassword;


    public PasswordWrapper() {

    }

    public PasswordWrapper(User user, String newPassword) {
        this.user = user;
        this.newPassword = newPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
