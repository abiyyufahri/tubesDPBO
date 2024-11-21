package com.springboot.tubespbo.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam("role") String role,
            RedirectAttributes redirAttrs,
            @RequestParam("tanggalLahir") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tanggalLahir,
            HttpSession session,
            Model model) {

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            redirAttrs.addFlashAttribute("message", "Email sudah terdaftar");
            return "redirect:/register";
        }

        User isSuccess = userService.register(username, email, password, noTelpon, jenisKelamin, tanggalLahir, role);
        if (isSuccess != null) {
            model.addAttribute("message", "Registration successful!");
            session.setAttribute("loggedUser", isSuccess);
            return "redirect:/dashboard";
        } else {
            redirAttrs.addFlashAttribute("message", "Email sudah ada atau password kurang dari 6 kata");
            return "redirect:/register";
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
            RedirectAttributes redirAttrs,
            Model model) {
        User isLoggedIn = userService.login(email, password);
        if (isLoggedIn != null) {
            session.setAttribute("loggedUser", isLoggedIn);
            return "redirect:/dashboard";
        } else {
            redirAttrs.addFlashAttribute("message", "Email atau password salah");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
