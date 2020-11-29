package com.example.medicine.service;

import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();
    List<UserRole> getUserRolesByUser(User user);
    UserRole createUserRole(UserRole userRoles);
    List<UserRole> getUsersByRoles(String role);
    void deleteUserRoles(Long id);
}
