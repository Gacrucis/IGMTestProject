package com.example.igmtestproject.controllers;

import com.example.igmtestproject.services.RateLimitedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/call-rate-limited")
public class RateLimitedController {

    private RateLimitedService rateLimitedService
            ;

    @GetMapping
    public ResponseEntity<String> generateHTML() throws Throwable {

        // Get result from calls
        String result = rateLimitedService.someIntensiveTask().toString();

        // Return 200 OK
        return ResponseEntity.ok(result);
    }

    @Autowired
    void setHtmlGeneratorService(RateLimitedService rateLimitedService){
        this.rateLimitedService = rateLimitedService;
    }
}
