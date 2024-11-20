package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;

import com.springboot.tubespbo.auditable.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {
    
    private String namaPesanan;
    private String riwayatPesanan;

    
    public Customer() {
    }

    public Customer(String username, String password, String email, String noTelpon, 
                    String jenisKelamin, boolean isActive, LocalDate tanggalLahir) {
        super(username, password, email, noTelpon, jenisKelamin, isActive,tanggalLahir);
    }

    
    public String getNamaPesanan() {
        return namaPesanan;
    }

    public void setNamaPesanan(String namaPesanan) {
        this.namaPesanan = namaPesanan;
    }

    public String getRiwayatPesanan() {
        return riwayatPesanan;
    }

    public void setRiwayatPesanan(String riwayatPesanan) {
        this.riwayatPesanan = riwayatPesanan;
    }
}
