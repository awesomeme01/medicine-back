package com.example.medicine.service;

import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    UserRoleRepository userRolesRepository;
    @Override
    public UserRole createUserRole(UserRole userRoles) {
        return userRolesRepository.save(userRoles);
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRolesRepository.findAll();
    }

    @Override
    public List<UserRole> getUserRolesByUser(User user) {
        return userRolesRepository.findAll().stream().filter(x->x.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserRole> getUsersByRoles(String role) {
        return userRolesRepository.findAll().stream().filter(x->x.getRole().equals(role)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserRoles(Long id) {
        userRolesRepository.deleteById(id);
    }
}
