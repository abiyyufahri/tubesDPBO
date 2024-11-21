package com.springboot.tubespbo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Pesanan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String jenisJasa;
    
    @NotNull
    private LocalDate waktu;

    @OneToOne
    private PenyediaJasa penyediaJasa;

    // @NotBlank
    // private Pembayaran pembayaran;

    
}
