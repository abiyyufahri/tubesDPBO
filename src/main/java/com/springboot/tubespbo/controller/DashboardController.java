package com.springboot.tubespbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String index(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login"; 
        }
        return "homeDashboard"; 
    }
}
