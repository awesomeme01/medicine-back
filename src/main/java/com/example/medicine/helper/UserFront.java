package com.example.medicine.helper;

import com.example.medicine.model.User;

public class UserFront {
    private User user;
    private String role;

    public UserFront(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
