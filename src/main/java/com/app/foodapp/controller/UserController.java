package com.app.foodapp.controller;

import com.app.foodapp.models.Users;
import com.app.foodapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;
    @GetMapping("/create")
    public ResponseEntity<Users> createUser() {
        Users user = new Users();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("pepito@gmail.com");
        user.setPassword("password");
        user.setImage("hola.png");
        user.setPhone("123456789");

        return ResponseEntity.ok(this.userServices.createUser(user));
    }
@GetMapping("/get-users")
    public  ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = this.userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
