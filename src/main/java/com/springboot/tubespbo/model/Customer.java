package com.springboot.tubespbo.model;

import com.springboot.tubespbo.auditable.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {
    private String namaPesanan;
    private String riwayatPesanan;
    
    // Getters and Setters
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
