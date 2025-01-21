package com.app.foodapp.controller;

import com.app.foodapp.models.Users;
import com.app.foodapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

@GetMapping("/get-users")
    public  ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = this.userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users newUser = this.userServices.createUser(user);
        return ResponseEntity.ok(newUser);
    }


}
