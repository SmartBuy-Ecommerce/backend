package com.ShopMinds.controller;

  // 👈 Adjust to match your package structure

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        System.out.println("Health check endpoint hit!");
        return Map.of(
                "status", "OK",
                "service", "your-service-name",
                "timestamp", java.time.Instant.now().toString()
        );
    }
}