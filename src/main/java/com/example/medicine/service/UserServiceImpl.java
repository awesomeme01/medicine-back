package com.example.medicine.service;

import com.example.medicine.helper.PasswordWrapper;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleService userRolesService;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(x-> {
            x.setRoles(new ArrayList<>());
            for(UserRole u : userRolesService.getUserRolesByUser(x)){
                x.addRoles(u.getRole());
            }
            return x;
        }).collect(Collectors.toList());
    }

    @Override
    public User changePassword(PasswordWrapper passwordWrapper) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newP = passwordEncoder.encode(passwordWrapper.getNewPassword());
        User user = passwordWrapper.getUser();
        user.setPassword(newP);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        UserRole ur = new UserRole("ROLE_USER", userRepository.findByUsername(user.getUsername()));
        userRolesService.createUserRole(ur);

        return user;
    }

    @Override
    public User disableUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setIsActive(0);
        userRepository.save(user);
        return user;
    }
    @Override
    public User activateUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setIsActive(1);
        userRepository.save(user);
        return user;
    }

    @Override
    public User register(User user) {
        userRepository.save(user);
        user.setIsActive(0);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
