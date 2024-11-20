package com.springboot.tubespbo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.auditable.User;
import com.springboot.tubespbo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Optional<User> findByUsername(String username);
    // boolean existsByUsername(String username);
    // boolean existsByEmail(String email);
}
