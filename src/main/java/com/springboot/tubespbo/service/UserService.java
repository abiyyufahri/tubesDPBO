package com.springboot.tubespbo.service;


import org.springframework.stereotype.Service;

import com.springboot.tubespbo.controller.AlertController;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.model.PenyediaJasa;
import com.springboot.tubespbo.model.User;
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
        return customerRepository.save(user);
    }

    
    public User login(String email, String rawPassword) {
        Optional<User> userOptional = customerRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return null;
            
        }

        User user = userOptional.get();

        if (!rawPassword.equals(user.getPassword())) {
            return null;
            
        }
        
        return user;
    }
}
