package com.ShopMinds.controller;

import com.ShopMinds.dto.UserDto;
import com.ShopMinds.model.User;
import com.ShopMinds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            User user = userService.signup(userDto);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> mockLogin() {
        return ResponseEntity.ok("Backend connected! (Login API works)");
    }

//    @GetMapping("/login")
//    public ResponseEntity<?>login(@RequestBody UserDto userDto) {
//        try{
//            User user = userService.login(userDto);
//            return ResponseEntity.ok(user);
//        }
//        catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

}
