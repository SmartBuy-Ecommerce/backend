package com.ShopMinds.controller;

import com.ShopMinds.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // ✅ Test endpoint to send welcome email manually
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, String> request) {
        String to = request.get("to");
        String name = request.get("name");

        if (to == null || name == null) {
            return ResponseEntity.badRequest().body("Missing 'to' or 'name' in request body");
        }

        emailService.sendWelcomeEmail(to, name);

        return ResponseEntity.ok("Welcome email sent successfully to " + to);
    }
}
