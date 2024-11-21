package com.springboot.tubespbo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private double jumlah;

    @OneToMany
    private List<Voucher> voucher;

    @OneToOne(mappedBy = "pembayaran")
    private RiwayatPesanan pesanan;

    public Pembayaran(){}

    public Pembayaran(@NotBlank double jumlah) {
        this.jumlah = jumlah;
    }

    public Long getId() {
        return id;
    }

    public double getJumlah() {
        return jumlah;
    }

    public List<Voucher> getVoucher() {
        return voucher;
    }

    
}
