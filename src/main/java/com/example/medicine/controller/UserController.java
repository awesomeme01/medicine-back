package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.helper.UserFront;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;
import com.example.medicine.service.UserRoleService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRolesService;

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/getAll")
    public Response getAll(Principal principal){
        try{
            return new Response(true, "Все пользователи","All User objects", userService.getAllUsers(), userRolesService.getUserRolesByUser(userService.getByUsername(principal.getName())));
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/getById/{id}")
    public Response getById(@PathVariable Long id){
        try{
            return new Response(true, "Пользователь с ID номером = " + id,"User with id = " + id, userService.getUserById(id));
        }
        catch (NoSuchElementException ex){
            return new Response(false, "Пользователь не найден!","User with id = " + id + " doesn't exist!", null,null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере", "Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }

    }
    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/create")
    public Response create(@RequestBody UserFront userFront){
        try{
            User user = userFront.getUser();
            userService.createUser(user);
            UserRole userRole = new UserRole(userFront.getRole(), user);
            userRolesService.createUserRole(userRole);
            return new Response(true, "Пользователь с ролью " + userRole.getRole() + " создан","Created new User object with role = " + userRole.getRole(), user, userRolesService.getUserRolesByUser(user));
        }
        catch (DataIntegrityViolationException ex){
            return new Response(false, "Не получилось создать пользователя! Произошла ошибка","Couldn't create user: ERROR: " + ex.getMessage(), ex.getStackTrace());
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/disable/{id}")
    public Response disableUser(@PathVariable Long id){
        try{
            return new Response(true, "Пользователь с ID номером " + id + " заблокирован","Disabled user with id = " + id, userService.disableUser(id));
        }
        catch (NoSuchElementException ex){
            return new Response(false, "Не существует пользователя с ID номером" + id, "No such user with id = " + id, null, null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/activate/{id}")
    public Response activateUser(@PathVariable Long id){
        try{
            return new Response(true,  "Пользователь с ID номером " + id + " активирован","Activate user with id = " + id, userService.activateUser(id));
        }
        catch(NoSuchElementException ex){
            return new Response(false, "Не существует пользователя с ID номером" + id,"No such user with id = " + id, null, null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/delete/{id}")
    public Response delete(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return new Response(true, "Пользователь удален", "Deleted user with id = " + id, null,null);
        }
        catch (EmptyResultDataAccessException exception){
            return new Response(false, "Error: " + exception.getMessage(), exception.getCause());
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }

    }
}
