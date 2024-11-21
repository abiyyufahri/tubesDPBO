package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "penyedia_jasa")
public class PenyediaJasa extends User {
    
    private String jenisKeahlian;
    private String statusKetersediaan;
    private int ratingPenilaian;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "penyediaJasa")
    private List<RiwayatPesanan> riwayatPesanan;

    public PenyediaJasa() {
    }

    public PenyediaJasa(String username, String password, String email, String noTelpon, 
                        String jenisKelamin, boolean isActive, LocalDate tanggalLahir) {
        super(username, password, email, noTelpon, jenisKelamin, isActive,tanggalLahir);
    }

    
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
