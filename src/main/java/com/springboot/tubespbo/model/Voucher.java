package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String kodeVoucher;
    
    @NotNull
    private int diskonPersen;

    @NotNull
    private LocalDate kadaluarsa;
    
    @NotNull
    private boolean statusAktif = true;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne(mappedBy = "voucher")
    private Pembayaran pembayaran;

    public Voucher(){
        
    }
    
    public Voucher(@NotEmpty String kodeVoucher, @NotEmpty int diskonPersen, @NotEmpty LocalDate kadaluarsa, @NotEmpty Customer customer) {
        this.kodeVoucher = kodeVoucher;
        this.diskonPersen = diskonPersen;
        this.kadaluarsa = kadaluarsa;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getKodeVoucher() {
        return kodeVoucher;
    }

    public double getDiskonPersen() {
        return diskonPersen;
    }

    public LocalDate getKadaluarsa() {
        return kadaluarsa;
    }

    public boolean isStatusAktif() {
        return statusAktif;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setStatusAktif(boolean statusAktif) {
        this.statusAktif = statusAktif;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }
    
    

    
}
