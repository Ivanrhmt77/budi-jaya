package com.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class WelcomeController {
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome ivan ganteng";
    }

    @GetMapping("/tes")
    public String postMethodName() {
        return "tes";
    }
    
}
