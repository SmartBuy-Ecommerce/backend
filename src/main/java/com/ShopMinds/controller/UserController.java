package com.ShopMinds.controller;

import com.ShopMinds.dto.UserDto;
import com.ShopMinds.exception.UserAlreadyExistsException;
import com.ShopMinds.model.User;
import com.ShopMinds.repository.UserRepository;
import com.ShopMinds.security.JwtUtill;
import com.ShopMinds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            User user = userService.signup(userDto);
            return ResponseEntity.ok(user);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    // AuthController.java (add this method)
    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmailAvailability(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Email parameter is required.")
            );
        }
        // Use the repository to check availability
        boolean isEmailAvailable = !userRepository.existsByEmail(email.trim().toLowerCase());

        return ResponseEntity.ok().body(
                Map.of("available", isEmailAvailable)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            User user = userService.login(userDto);
            // No need to check password again here!
            String token = JwtUtill.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
