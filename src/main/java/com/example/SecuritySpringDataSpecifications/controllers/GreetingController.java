package com.example.SecuritySpringDataSpecifications.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class GreetingController {

    @GetMapping("home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/adminOnly")
    public String getOnlyAdminContent() {
        return "admin";
    }

    @GetMapping("/authOnly")
    public String getOnlyAuthenticatedUserPassword() {
        return "authOnly";
    }
}
