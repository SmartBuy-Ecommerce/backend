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

import java.util.HashMap;
import java.util.List;
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
            String token = JwtUtill.generateToken(user.getEmail());

            // Create response object with token and user data
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "email", user.getEmail(),
                    "role", user.getRole(),
                    "name", user.getName()
            ));

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestParam("id") int id) {
        try{
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/updateUserStatus")
    public ResponseEntity<?> updateUserStatus(
            @RequestParam("id") int id,
            @RequestParam("status")  String status) {
        try {
            User updatedUserStatus = userService.updateUserStatus(id, status);
            return ResponseEntity.ok(updatedUserStatus);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user status");
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam("id") int id, @RequestBody UserDto userDto) {
        try {
            User updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam("id") int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
