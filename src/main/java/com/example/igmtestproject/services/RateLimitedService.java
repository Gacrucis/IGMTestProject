package com.example.igmtestproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateLimitedService {

    private MockMethodsService mockMethodsService;

    public List<String> someIntensiveTask() throws Throwable {
        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            results.add(mockMethodsService.getRateLimitedString().getBody());
        }

        return results;
    }

    @Autowired
    public void setMockMethodsService(MockMethodsService mockMethodsService){
        this.mockMethodsService = mockMethodsService;
    }
}
