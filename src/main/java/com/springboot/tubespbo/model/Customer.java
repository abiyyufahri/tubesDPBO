package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {
    
    private String namaPesanan;
    // private String riwayatPesanan;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alamat_id")
    private Alamat alamat;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<RiwayatPesanan> riwayatPesanan;

    
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

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public void addRiwayatPesanan(RiwayatPesanan riwayatPesanan) {
        this.riwayatPesanan.add(riwayatPesanan);
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public List<RiwayatPesanan> getRiwayatPesanan() {
        return riwayatPesanan;
    }

    
}
