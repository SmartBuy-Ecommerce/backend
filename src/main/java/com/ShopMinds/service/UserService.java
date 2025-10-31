package com.ShopMinds.service;

import com.ShopMinds.dto.UserDto;
import com.ShopMinds.exception.UserAlreadyExistsException; // Make sure to import the exception
import com.ShopMinds.model.User;
import com.ShopMinds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ShopMinds.model.Status.APPROVED;
import static com.ShopMinds.model.Status.PENDING;
import static com.ShopMinds.model.UserRole.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public User login(UserDto userDto) {
        // Find user by email

        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if(String.valueOf(PENDING).equalsIgnoreCase(user.getStatus())){
            throw new RuntimeException("Your account is not approved yet. Please wait for admin approval before logging in.");
        }

        // Check password (using PasswordEncoder for security)
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // If everything is correct, return the user
        return user;
    }

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

        String role = userDto.getRole() != null ? userDto.getRole() : String.valueOf(APPROVED);
        user.setRole(role);
        // Set default role for all new users
        if(String.valueOf(BUYER).equals(role)) {
            user.setStatus(String.valueOf(APPROVED));
        }
        else if(String.valueOf(SELLER).equals(role)) {
            user.setStatus(String.valueOf(PENDING));
        }
//        if(user.getRole() == String.valueOf(BUYER)) {
//            user.setStatus(String.valueOf(APPROVED));
//        }
//        else{
//            user.setStatus(String.valueOf(PENDING)  );
//        }

        emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getName());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUserStatus(int id, String status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the status
        user.setStatus(status); // Make sure you have this setter method

        // Save the updated user
        return userRepository.save(user);
    }
}