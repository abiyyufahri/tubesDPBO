package com.springboot.tubespbo.model;

import com.springboot.tubespbo.auditable.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ChatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @OneToOne
    private User pengguna;

    @NotBlank
    private String isiPesan;

    public Long getId() {
        return id;
    }

    public User getPengguna() {
        return pengguna;
    }

    public String getIsiPesan() {
        return isiPesan;
    } 
}
