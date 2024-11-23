package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alamat_id")
    private Alamat alamat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<RiwayatPesanan> riwayatPesanan = new ArrayList<>();    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Voucher> vouchers = new ArrayList<>();

    
    public Customer() {
    }

    public Customer(String username, String password, String email, String noTelpon, 
                    String jenisKelamin, boolean isActive, LocalDate tanggalLahir) {
        super(username, password, email, noTelpon, jenisKelamin, isActive,tanggalLahir);
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public void addRiwayatPesanan(RiwayatPesanan riwayatPesanan) {
        if (this.riwayatPesanan == null) {
            this.riwayatPesanan = new ArrayList<>();
        }
        this.riwayatPesanan.add(riwayatPesanan);
    }

    public void addVoucher(Voucher voucher) {
        if (this.vouchers == null) {
            this.vouchers = new ArrayList<>();
        }
        this.vouchers.add(voucher);
    }


    public Alamat getAlamat() {
        return alamat;
    }

    public List<RiwayatPesanan> getRiwayatPesanan() {
        return riwayatPesanan;
    }

    
}
