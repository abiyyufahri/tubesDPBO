package com.springboot.tubespbo.service;


import org.springframework.stereotype.Service;

import com.springboot.tubespbo.auditable.User;
import com.springboot.tubespbo.controller.AlertController;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.model.PenyediaJasa;
import com.springboot.tubespbo.repository.CustomerRepository;
import com.springboot.tubespbo.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository customerRepository;

    public UserService(UserRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    
    public User register(String username, String email, String rawPassword, String noTelpon, String jenisKelamin, LocalDate tanggalLahir, String role) {
        
        if (customerRepository.existsByEmail(email)) {
            return null;
        }

        User user;
        if(role.equals("Customer")){
            user = new Customer(username, rawPassword, email, noTelpon, jenisKelamin, true, tanggalLahir);
        }else{
            user = new PenyediaJasa(username, rawPassword, email, noTelpon, jenisKelamin, true, tanggalLahir);
        }
        // user.setUsername(username);
        // user.setEmail(email);
        // user.setPassword(rawPassword); 

        return customerRepository.save(user);
    }

    
    public User login(String email, String rawPassword) {
        Optional<User> userOptional = customerRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        if (!rawPassword.equals(user.getPassword())) {
            return null;
            // throw new RuntimeException("Invalid password");
        }
        // System.out.println("AAA : "+rawPassword.equals(user.getPassword()));
        return user;
    }
}
