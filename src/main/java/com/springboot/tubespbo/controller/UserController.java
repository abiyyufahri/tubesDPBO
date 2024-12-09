package com.springboot.tubespbo.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.model.PenyediaJasa;
import com.springboot.tubespbo.model.User;
import com.springboot.tubespbo.repository.CustomerRepository;
import com.springboot.tubespbo.repository.PenyediaJasaRepository;
import com.springboot.tubespbo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PenyediaJasaRepository penyediaJasaRepository;

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
        try {
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                redirAttrs.addFlashAttribute("message", "Email sudah terdaftar");
                return "redirect:/register";
            }

            User isLoggedIn = userService.register(username, email, password, noTelpon, jenisKelamin, tanggalLahir,
                    role);
            if (isLoggedIn != null) {
                Sessiondata sessiondata = new Sessiondata(isLoggedIn,
                        isLoggedIn instanceof Customer ? "Customer" : "Penyedia Jasa");
                model.addAttribute("message", "Registration successful!");
                session.setAttribute("loggedUser", sessiondata);
                return "redirect:/dashboard";
            } else {
                redirAttrs.addFlashAttribute("message", "Email sudah ada atau password kurang dari 6 kata");
                return "redirect:/register";
            }
        } catch (Exception e) {
            System.err.println(e);
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
        try {
            User isLoggedIn = userService.login(email, password);
            Sessiondata sessiondata;
            if (isLoggedIn != null) {
                if (isLoggedIn instanceof Customer) {
                    Optional<Customer> data = customerRepository.findById(isLoggedIn.getId());
                    sessiondata = new Sessiondata(data.orElseThrow(() -> new RuntimeException("User id not found")),
                            "Customer");
                    session.setAttribute("loggedUser", sessiondata);
                } else {
                    Optional<PenyediaJasa> data = penyediaJasaRepository.findById(isLoggedIn.getId());
                    sessiondata = new Sessiondata(data.orElseThrow(() -> new RuntimeException("User id not found")),
                            "Penyedia Jasa");
                    session.setAttribute("loggedUser", sessiondata);
                }
                return "redirect:/dashboard";
            } else {
                redirAttrs.addFlashAttribute("message", "Email atau password salah");
                return "redirect:/login";
            }
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
