package com.example.igmtestproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateLimitedService {

    private MockMethodsService mockMethodsService;

    public ResponseEntity<String> callAPI(){

        // Call mock API (rate limit is 3 calls per 3 seconds)
        return mockMethodsService.getRateLimitedString();
    }

    public List<String> someIntensiveTask(){
        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            results.add(this.callAPI().getBody());
        }

        return results;
    }

    @Autowired
    public void setMockMethodsService(MockMethodsService mockMethodsService){
        this.mockMethodsService = mockMethodsService;
    }
}
