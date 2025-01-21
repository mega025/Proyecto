package com.app.foodapp.controller;

import com.app.foodapp.models.Roles;
import com.app.foodapp.services.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolServices rolServices;

    @GetMapping("/get-rol")
    public ResponseEntity<List<Roles>> getAllRol() {
        List<Roles> roles = rolServices.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Roles> getRolByName(@PathVariable String name) {
        Roles roles = rolServices.getRolesByRolName(name);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/create")
    public ResponseEntity<Roles> createRol(@RequestBody Roles rol) {
        Roles role = this.rolServices.createRole(rol);
        return ResponseEntity.ok(role);
    }
}
