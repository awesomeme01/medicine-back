package com.example.medicine.service;

import com.example.medicine.helper.PasswordWrapper;
import com.example.medicine.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    void deleteUser(Long id);
    User changePassword(PasswordWrapper passwordWrapper);
    User getByUsername(String username);
    User activateUser(Long id);
    User disableUser(Long id);
}
