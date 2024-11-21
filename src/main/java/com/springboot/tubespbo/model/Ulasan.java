package com.springboot.tubespbo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @OneToOne
    private Pesanan pesanan;

    @NotBlank
    private double rating;


    private String komentar;
    
    @NotNull
    private LocalDate tanggalUlasan;
   

    public Ulasan(Long id, @NotNull Pesanan pesanan, @NotBlank double rating, String komentar,
            @NotNull LocalDate tanggalUlasan) {
        this.id = id;
        this.pesanan = pesanan;
        this.rating = rating;
        this.komentar = komentar;
        this.tanggalUlasan = tanggalUlasan;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }


}
