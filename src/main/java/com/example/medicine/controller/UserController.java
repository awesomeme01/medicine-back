package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.model.User;
import com.example.medicine.service.UserRoleService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRolesService;

    @Secured("ROLE_USER")
    @GetMapping(path = "/getAll")
    public Response getAll(Principal principal){
        try{
            return new Response(true, "All User objects", userService.getAllUsers(), userRolesService.getUserRolesByUser(userService.getByUsername(principal.getName())));
        }
        catch (Exception ex){
            return new Response(false, "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_USER")
    @GetMapping(path = "/getById/{id}")
    public Response getById(@PathVariable Long id){
        try{
            return new Response(true, "User with id = " + id, userService.getUserById(id));
        }
        catch (NoSuchElementException ex){
            return new Response(false, "User with id = " + id + " doesn't exist!", null);
        }
        catch (Exception ex){
            return new Response(false, "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/create")
    public Response create(@RequestBody User user){
        return new Response(true, "Created new User object", userService.createUser(user), userRolesService.getUserRolesByUser(user));
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/disable/{id}")
    public Response disableUser(@PathVariable Long id){
        try{
            return new Response(true, "Disabled user with id = " + id, userService.disableUser(id));
        }
        catch (NoSuchElementException ex){
            return new Response(false, "No such user with id = " + id, null);
        }
        catch (Exception ex){
            return new Response(false, "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/activate/{id}")
    public Response activateUser(@PathVariable Long id){
        try{
            return new Response(true, "Activate user with id = " + id, userService.activateUser(id));
        }
        catch(NoSuchElementException ex){
            return new Response(false, "No such user with id = " + id, null);
        }
        catch (Exception ex){
            return new Response(false, "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/delete/{id}")
    public Response delete(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return new Response(true, "Deleted user with id = " + id, null);
        }
        catch (EmptyResultDataAccessException exception){
            return new Response(false, "Error: " + exception.getMessage(), exception.getCause());
        }

    }
}
