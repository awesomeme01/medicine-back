package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.service.UserRoleService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/userRoles")
public class UserRoleController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleService userRolesService;

    @GetMapping(path = "/getAll")
    public Response getAllUserRoles(){
        return new Response(true, "All UserRole objects", userRolesService.getAllUserRoles());
    }
    @GetMapping(path = "/getAllByUserId/{id}")
    public Response getUserRolesByUser(@PathVariable Long id){
        User user;
        try{
            user = userRepository.findById(id).get();
            return new Response(true, "", userRolesService.getUserRolesByUser(user));
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new Response(false, "No such user with id = " + id + " exists. Error: " + e.getMessage(), e.getCause());
        }
        catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }

    }
    @GetMapping(path = "/getAllByRole/{role}")
    public Response getUsersByRole(@PathVariable String role){
        return new Response(true, "Users with role = " + role, userRolesService.getUsersByRoles(role));
    }
    @PostMapping(path = "/createForUser/{userId}")
    public Response create(@RequestBody UserRole userRole, @PathVariable Long userId){
        User user;
        try{
            user = userRepository.findById(userId).get();
            userRole.setUser(user);
            return new Response(true,"Created new UserRole object",userRolesService.createUserRole(userRole));
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new Response(false, "No such user with id = " + userId + ". Error: " + e.getMessage(), e);
        }
        catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }


    }
    @DeleteMapping(path = "/delete/{id}")
    public Response deleteUserRole(@PathVariable Long id){
        try{
            userRolesService.deleteUserRoles(id);
            return new Response(true, "Deleted UserRole object with id = " + id, null);
        }
        catch (EmptyResultDataAccessException exception){
            return new Response(true, "Error: " + exception.getMessage(), exception.getCause());
        }
        catch (Exception ex){
            return new Response(false, "Unexpected error!", ex.getMessage());
        }
    }
}
