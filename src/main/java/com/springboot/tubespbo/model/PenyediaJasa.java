package com.springboot.tubespbo.model;

import com.springboot.tubespbo.auditable.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "penyedia_jasa")
public class PenyediaJasa extends User {
    
    private String jenisKeahlian;
    private String statusKetersediaan;
    private int ratingPenilaian;
    
    // Getters and Setters
    public String getJenisKeahlian() {
        return jenisKeahlian;
    }

    public void setJenisKeahlian(String jenisKeahlian) {
        this.jenisKeahlian = jenisKeahlian;
    }

    public String getStatusKetersediaan() {
        return statusKetersediaan;
    }

    public void setStatusKetersediaan(String statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }

    public int getRatingPenilaian() {
        return ratingPenilaian;
    }

    public void setRatingPenilaian(int ratingPenilaian) {
        this.ratingPenilaian = ratingPenilaian;
    }
}
