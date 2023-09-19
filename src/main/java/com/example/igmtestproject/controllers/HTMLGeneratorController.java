package com.example.igmtestproject.controllers;

import com.example.igmtestproject.services.HTMLGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/generate")
public class HTMLGeneratorController {

    private HTMLGeneratorService htmlGeneratorService;

    @GetMapping("/html")
    public ResponseEntity<String> generateHTML() throws InterruptedException, ExecutionException {

        // Get HTML from service
        String htmlFile = htmlGeneratorService.generateHTML();

        // Return 200 OK
        return ResponseEntity.ok(htmlFile);
    }

    @Autowired
    void setHtmlGeneratorService(HTMLGeneratorService htmlGeneratorService){
        this.htmlGeneratorService = htmlGeneratorService;
    }

}
