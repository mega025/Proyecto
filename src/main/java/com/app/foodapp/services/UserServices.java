package com.app.foodapp.services;

import com.app.foodapp.models.Roles;
import com.app.foodapp.models.Users;
import com.app.foodapp.repositories.RolRepository;
import com.app.foodapp.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;

    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }
    public Optional<Users> getUserById(Long id) {
    return this.userRepository.findById(id);
    }
    public Optional<Users> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void DeleteUserById(Long id) {
        Users userOptional = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrdo " + id));
        this.userRepository.delete(userOptional);
    }


    public Users createUser(Users user) {

        if ( this.userRepository.findByEmail(user.getEmail()).isPresent()){
    throw new RuntimeException("Usuario ya existe");
}

        Users newUser = new Users();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhone(user.getPhone());
        newUser.setImage("");

        Set<Roles> roles = new HashSet<>();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Roles defualtRol = this.rolRepository.findRolesByName("admin");
            if (defualtRol != null) {
                roles.add(defualtRol);
            }else {
                throw new RuntimeException("No se puede crear el rol");
            }
        }else {
            for (Roles role : user.getRoles()) {
                Roles newRoles = this.rolRepository.findRolesByName(role.getName());
                roles.add(newRoles);
            }
        }
        newUser.setRoles(roles);
        return this.userRepository.save(newUser);
    }

}
