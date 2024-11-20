package com.springboot.tubespbo.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springboot.tubespbo.auditable.User;
import com.springboot.tubespbo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("noTelpon") String noTelpon,
            @RequestParam("jenisKelamin") String jenisKelamin,
            @RequestParam("tanggalLahir") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tanggalLahir,
            HttpSession session,
            Model model) {

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }

        User isSuccess = userService.register(username, email, password, noTelpon, jenisKelamin, tanggalLahir);
        if (isSuccess != null) {
            model.addAttribute("message", "Registration successful!");
            session.setAttribute("loggedUser", isSuccess);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Registration failed. Try again.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        User isLoggedIn = userService.login(email, password);
        if (isLoggedIn != null) {
            session.setAttribute("loggedUser", isLoggedIn);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
