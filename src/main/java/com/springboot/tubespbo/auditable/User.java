package com.springboot.tubespbo.auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String username;
    
    @NotBlank
    @Size(min = 6)
    @JsonIgnore
    private String password;
    
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    
    private String noTelpon;
    private String alamat;
    
    private String role;
    
    private boolean isActive;
}