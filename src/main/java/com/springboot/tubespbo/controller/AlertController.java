package com.springboot.tubespbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlertController {

    @GetMapping("/showAlert")
    public String showAlert(Model model) {
        model.addAttribute("message", "Hello, this is an alert from Spring Boot!");
        return "alert";
    }
}

