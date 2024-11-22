package com.springboot.tubespbo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String kodeVoucher;
    
    @NotEmpty
    private double diskonPersen;

    @NotEmpty
    private Date tanggalBuat;
    
    @NotEmpty
    private double jumlah;
    
    @NotEmpty
    private boolean statusAktif;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne(mappedBy = "voucher")
    private Pembayaran pembayaran;

    public Voucher(){
        
    }
    
    public Voucher(Long id, @NotEmpty String kodeVoucher, @NotEmpty double diskonPersen, @NotEmpty Date tanggalBuat,
            @NotEmpty double jumlah, @NotEmpty boolean statusAktif) {
        this.id = id;
        this.kodeVoucher = kodeVoucher;
        this.diskonPersen = diskonPersen;
        this.tanggalBuat = tanggalBuat;
        this.jumlah = jumlah;
        this.statusAktif = statusAktif;
    }
    
    public void aktifkanVoucher(){
        statusAktif = true;
    }
    
    public void nonaktifkanVoucher(){
        statusAktif = false;
    }
    
    public boolean isStatusAktif(){
        return statusAktif;
    }
    
    public String getKodeVoucher(){
        return kodeVoucher;
    }

    public Long getId() {
        return id;
    }

    public double getDiskonPersen() {
        return diskonPersen;
    }

    public Date getTanggalBuat() {
        return tanggalBuat;
    }

    public double getJumlah() {
        return jumlah;
    }

    public Customer getCustomer() {
        return customer;
    }

    
}
