package com.app.foodapp.services;

import com.app.foodapp.models.Users;
import com.app.foodapp.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

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
    public Users addUser(Users user, Long id) {
        Users userOptional = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrdo " + id));

        userOptional.setEmail(user.getEmail());
        userOptional.setPassword(user.getPassword());
        userOptional.setFirstName(user.getFirstName());
        userOptional.setLastName(user.getLastName());
        userOptional.setImage(user.getImage());
        userOptional.setPhone(user.getPhone());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userOptional.setPassword(user.getPassword());
        }
        return this.userRepository.save(userOptional);
    }
    public Users createUser(Users user) {
        return this.userRepository.save(user);
    }

}
