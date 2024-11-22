package com.springboot.tubespbo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import jakarta.validation.constraints.Min;
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
    @Min(1)
    private int status;
    
    @NotNull
    private LocalDateTime waktu;

    @ManyToOne
    @JoinColumn(name = "id_penyediaJasa")
    private PenyediaJasa penyediaJasa;

    @OneToOne(cascade = CascadeType.ALL)
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

    public RiwayatPesanan(@NotBlank String jenisJasa, @NotBlank int status, @NotNull LocalDateTime waktu,
            Pembayaran pembayaran, @NotNull Customer customer) {
        this.jenisJasa = jenisJasa;
        this.status = status;
        this.waktu = waktu;
        this.pembayaran = pembayaran;
        this.customer = customer;
    }



    public Long getId() {
        return id;
    }

    public String getJenisJasa() {
        return jenisJasa;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public PenyediaJasa getPenyediaJasa() {
        return penyediaJasa;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public int getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Ulasan getUlasan() {
        return ulasan;
    }
    
    public String formatWaktu() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", java.util.Locale.forLanguageTag("id-ID"));
        return waktu.format(formatter);
    }

    public String getNamaJenisJasa() {
        if ("1".equals(this.jenisJasa)) {
            return "Jasa Pembersih Ruangan";
        } else if ("2".equals(this.jenisJasa)) {
            return "Jasa Cat Ruangan";
        } else if ("3".equals(this.jenisJasa)) {
            return "Jasa Servis AC";
        } else if ("4".equals(this.jenisJasa)) {
            return "Jasa Perbaiki TV";
        } else if ("5".equals(this.jenisJasa)) {
            return "Jasa Perbaiki Perabot";
        } else if ("6".equals(this.jenisJasa)) {
            return "Jasa Pemotong Rumput";
        } else {
            return "Jenis Jasa Tidak Diketahui";
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJenisJasa(String jenisJasa) {
        this.jenisJasa = jenisJasa;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setWaktu(LocalDateTime waktu) {
        this.waktu = waktu;
    }

    public void setPenyediaJasa(PenyediaJasa penyediaJasa) {
        this.penyediaJasa = penyediaJasa;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setUlasan(Ulasan ulasan) {
        this.ulasan = ulasan;
    }    
}
