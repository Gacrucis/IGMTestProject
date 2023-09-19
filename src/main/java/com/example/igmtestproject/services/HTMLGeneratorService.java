package com.example.igmtestproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class HTMLGeneratorService {

    private MockMethodsService mockMethodsService;

    public String generateHTML() throws InterruptedException, ExecutionException {
        // Create tasks
        CompletableFuture<String> htmlHeader = mockMethodsService.getHTMLComponentHead();
        CompletableFuture<String> htmlBody = mockMethodsService.getHTMLComponentBody();
        CompletableFuture<String> htmlFooter = mockMethodsService.getHTMLComponentFooter();

        // Wait until complete
        CompletableFuture.allOf(htmlHeader, htmlBody, htmlFooter);

        // Join components and return
        return htmlHeader.get() + htmlBody.get() + htmlFooter.get();
    }

    @Autowired
    public void setMockMethodsService(MockMethodsService mockMethodsService){
        this.mockMethodsService = mockMethodsService;
    }
}
