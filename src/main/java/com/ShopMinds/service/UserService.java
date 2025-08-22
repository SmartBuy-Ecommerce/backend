package com.ShopMinds.service;

import com.ShopMinds.dto.UserDto;
import com.ShopMinds.exception.UserAlreadyExistsException; // Make sure to import the exception
import com.ShopMinds.model.User;
import com.ShopMinds.model.UserRole;
import com.ShopMinds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public User signup(UserDto userDto) {
        // Check if email already exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("Email is already registered: " + userDto.getEmail());
        }

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

    public User login(UserDto userDto) {
        // Find user by email

        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check password (using PasswordEncoder for security)
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // If everything is correct, return the user
        return user;
    }
}