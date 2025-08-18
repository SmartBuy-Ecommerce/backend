package com.ShopMinds.service;


import com.ShopMinds.dto.UserDto;
import com.ShopMinds.model.User;
import com.ShopMinds.model.UserRole;
import com.ShopMinds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public User signup(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // hashed
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        // Set default role for all new users
        user.setRole(UserRole.BUYER); // ✅ sets the default role

        emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getName());
        return userRepository.save(user);
    }




    public User login(String email, String password) {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check password (⚠️ in production use password hashing like BCrypt)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        // If everything is correct, return the user
        return user;
    }

}
