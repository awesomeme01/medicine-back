package com.example.medicine.bootstrap;

import com.example.medicine.enums.Gender;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.service.UserRoleService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
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

        try{
            User user1 = new User.Builder("admin").withFullname("Админ Админ").withPhonenumber("+996555348520").withEmail("admin@gmail.com").withDateOfBirth(LocalDateTime.now().plusHours(6)).withPassword("123").withGender(Gender.NONE).isActive(1).build();
            User user2 = new User.Builder("cashier").withFullname("Кассир Кассир").withPhonenumber("+996555348521").withEmail("cashier@gmail.com").withDateOfBirth(LocalDateTime.now().plusHours(6)).withPassword("123").withGender(Gender.NONE).isActive(1).build();
            User user3 = new User.Builder("reception").withFullname("Приемная Приемная").withPhonenumber("+996555348522").withEmail("reception@gmail.com").withDateOfBirth(LocalDateTime.now().plusHours(6)).withPassword("123").withGender(Gender.FEMALE).isActive(1).build();
            User user4 = new User.Builder("doctor").withFullname("Врач Врач").withPhonenumber("+996555438523").withEmail("doctor@gmail.com").withDateOfBirth(LocalDateTime.now().plusHours(6)).withPassword("123").withGender(Gender.FEMALE).isActive(1).build();
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
            userService.createUser(user4);
            UserRole userRole1 = new UserRole("ROLE_ADMIN", user1);
            UserRole userRole2 = new UserRole("ROLE_CASHIER", user2);
            UserRole userRole3 = new UserRole("ROLE_RECEPTION", user3);
            UserRole userRole4 = new UserRole("ROLE_DOCTOR", user4);
            userRoleService.createUserRole(userRole1);
            userRoleService.createUserRole(userRole2);
            userRoleService.createUserRole(userRole3);
            userRoleService.createUserRole(userRole4);
        }catch (DataIntegrityViolationException exception){
            System.out.println("Command line runner didn't create objects because they already exist!");
        }

    }
}



