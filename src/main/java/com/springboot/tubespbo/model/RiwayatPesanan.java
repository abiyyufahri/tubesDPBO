package com.springboot.tubespbo.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "riwayatPesanan")
public class RiwayatPesanan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String jenisJasa;
    
    @NotNull
    private LocalDate waktu;

    @ManyToOne
    @JoinColumn(name = "id_penyediaJasa")
    private PenyediaJasa penyediaJasa;

    @OneToOne
    @JoinColumn(name = "id_pembayaran")
    private Pembayaran pembayaran;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "id_ulasan")
    private Ulasan ulasan;

    public RiwayatPesanan(){}

    public RiwayatPesanan(Long id, @NotBlank String jenisJasa, @NotNull LocalDate waktu, Pembayaran pembayaran) {
        this.id = id;
        this.jenisJasa = jenisJasa;
        this.waktu = waktu;
        this.pembayaran = pembayaran;
    }

    public Long getId() {
        return id;
    }

    public String getJenisJasa() {
        return jenisJasa;
    }

    public LocalDate getWaktu() {
        return waktu;
    }

    public PenyediaJasa getPenyediaJasa() {
        return penyediaJasa;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }
    
}
