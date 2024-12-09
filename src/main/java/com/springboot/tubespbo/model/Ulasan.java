package com.springboot.tubespbo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ulasan")
public class Ulasan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(mappedBy = "ulasan", cascade = CascadeType.ALL)
    private RiwayatPesanan pesanan;

    @NotNull
    private int rating;


    private String komentar;
    
    @NotNull
    private LocalDateTime createdAt;
   
    public Ulasan(){}

    public Ulasan(@NotNull RiwayatPesanan pesanan, @NotNull int rating, String komentar) {
        this.pesanan = pesanan;
        this.rating = rating;
        this.komentar = komentar;
        this.createdAt = LocalDateTime.now();
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public int getRating() {
        return rating;
    }

    public String getKomentar() {
        return komentar;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
