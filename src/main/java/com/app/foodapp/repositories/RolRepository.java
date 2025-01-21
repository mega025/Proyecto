package com.app.foodapp.repositories;

import com.app.foodapp.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Roles,Long> {
    Roles findRolesByName(String name);

}
