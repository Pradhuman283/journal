package com.algoknight.journalApp.contoller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChecker {

    @GetMapping("/health-checker")
    public String healthchecker(){
        return "ok";
    }
}
