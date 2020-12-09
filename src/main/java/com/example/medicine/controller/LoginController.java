package com.example.medicine.controller;

import com.example.medicine.helper.PasswordWrapper;
import com.example.medicine.helper.Response;
import com.example.medicine.model.User;
import com.example.medicine.service.UserRoleService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRolesService;
    @GetMapping
    @RequestMapping(value = "/myUser", method = RequestMethod.GET)
    @ResponseBody
    public Response currentUser(Principal principal){
        try{
            User user  = userService.getByUsername(principal.getName());
            return new Response(true, "Login successful! Current user information", user, userRolesService.getUserRolesByUser(user));
        }
        catch (NoSuchElementException e){
            return new Response(false, "Login failed! Error: " + e.getMessage(), e.getStackTrace());
        }
        catch (Exception e){
            return new Response(false, "Unexpected error! \n" + e.getMessage(), e.getStackTrace());
        }
    }
    @GetMapping
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    @ResponseBody
    public Response registerUser(@RequestBody User user){
        try{
            return new Response(true, "Registration successful!", userService.register(user));
        }
        catch (Exception e){
            return new Response(false, "Unexpected error! \n" + e.getMessage(), e.getStackTrace());
        }
    }

    @Secured("ROLE_USER")
    @PostMapping(path = "/changePassword")
    public Response changePassword(@RequestBody PasswordWrapper passwordWrapper, Principal principal) {
        passwordWrapper.setUser(userService.getByUsername(principal.getName()));
        try{
            return new Response(true, "Password Changed successfully!", userService.changePassword(passwordWrapper));
        }
        catch (Exception e){
            return new Response(false, "Unexpected error! \n" + e.getMessage(), e.getStackTrace());
        }
    }


}
