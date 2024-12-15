package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "penyedia_jasa")
public class PenyediaJasa extends User implements UserInterface{
    
    private String jenisKeahlian;
    
    @NotNull
    private boolean isTersedia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "penyediaJasa", fetch = FetchType.EAGER)
    private List<RiwayatPesanan> riwayatPesanan = new ArrayList<>();

    public PenyediaJasa() {
    }

    public PenyediaJasa(String username, String password, String email, String noTelpon, 
                        String jenisKelamin, boolean isActive, LocalDate tanggalLahir) {
        super(username, password, email, noTelpon, jenisKelamin, isActive,tanggalLahir);
        this.isTersedia = true;
    }

    
    public String getJenisKeahlian() {
        return jenisKeahlian;
    }

    public void setJenisKeahlian(String jenisKeahlian) {
        this.jenisKeahlian = jenisKeahlian;
    }

    public boolean isTersedia() {
        return isTersedia;
    }

    public void setTersedia(boolean isTersedia) {
        this.isTersedia = isTersedia;
    }

    public List<RiwayatPesanan> getRiwayatPesanan() {
        return riwayatPesanan;
    }

    @Override
    public void addRiwayatPesanan(RiwayatPesanan riwayatPesanan) {
        this.riwayatPesanan.add(riwayatPesanan);
    }
    
}
