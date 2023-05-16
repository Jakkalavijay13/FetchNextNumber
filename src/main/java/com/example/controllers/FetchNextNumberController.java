package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.FetchNextNumberService;

@RestController
public class FetchNextNumberController {
    @Autowired
    private FetchNextNumberService fetchNextNumberService;

    @GetMapping("/nextNumber/{categoryCode}")
    public String fetchNextNumber(@PathVariable String categoryCode) {
        return fetchNextNumberService.fetchNextNumber(categoryCode);
    }
}
