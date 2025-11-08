package com.ShopMinds.controller;

import com.ShopMinds.model.Contact;
import com.ShopMinds.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/contactus")
    public ResponseEntity<?> contactus(@RequestBody Contact contact) {
        System.out.println("Received contact request from: " + contact.getEmail());
        try {
            Contact con = contactService.contactus(contact);
            return ResponseEntity.ok(con);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing contact form");
        }
    }
}
