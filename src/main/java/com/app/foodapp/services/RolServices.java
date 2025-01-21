package com.app.foodapp.services;

import com.app.foodapp.models.Roles;
import com.app.foodapp.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RolServices {
    @Autowired
    private RolRepository rolRepository;

    public List<Roles> getAllRoles() {
        return rolRepository.findAll();
    }
    public Roles getRolesByRolName(String rolName) {
        return rolRepository.findRolesByName(rolName);
    }
    public Roles createRole(Roles role) {

        Roles newRole = rolRepository.save(role);
        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());
        newRole.setImage("");
        return this.rolRepository.save(newRole);
    }
}
