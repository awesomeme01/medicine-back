package com.example.medicine.bootstrap;

import com.example.medicine.enums.Gender;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.repository.UserRoleRepository;
import com.example.medicine.service.UserRoleService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MainBootstrap implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleService userRoleService;
    @Override
    public void run(String... args) throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        User user1 = new User.Builder("admin").withFullname("Админ Админ").withPhonenumber("+996555348520").withEmail("admin@gmail.com").withDateOfBirth(LocalDateTime.now().plusHours(6)).withPassword(passwordEncoder.encode("123")).withGender(Gender.NONE).isActive(1).build();
//        User user2 = new User.Builder("registrationService").withFullname("registrationService").withPhonenumber("0").withEmail("e").withDateOfBirth(LocalDateTime.now().plusHours(6)).withPassword(passwordEncoder.encode("1267476Sha")).withGender(Gender.NONE).isActive(1).build();
//        userService.createUser(user1);
//        userRepository.save(user2);
//        UserRole userRole1 = new UserRole("ROLE_ADMIN",user1);
//        UserRole userRole2 = new UserRole("ROLE_WEBAPP",user2);
//        userRoleService.createUserRole(userRole1);
//        userRoleService.createUserRole(userRole2);

    }
}



