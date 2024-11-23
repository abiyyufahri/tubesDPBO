package com.springboot.tubespbo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @Column(name = "status_aktif")
    private boolean statusAktif;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne(mappedBy = "voucher", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private Pembayaran pembayaran;

    @NotNull
    private LocalDateTime createdAt;

    public Voucher(){
        
    }
    
    public Voucher(@NotEmpty String kodeVoucher, @NotEmpty int diskonPersen, @NotEmpty LocalDate kadaluarsa, @NotEmpty Customer customer) {
        this.kodeVoucher = kodeVoucher;
        this.diskonPersen = diskonPersen;
        this.kadaluarsa = kadaluarsa;
        this.customer = customer;
        this.statusAktif = true;
        this.createdAt = LocalDateTime.now();
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

    public void setKodeVoucher(String kodeVoucher) {
        this.kodeVoucher = kodeVoucher;
    }

    public void setDiskonPersen(int diskonPersen) {
        this.diskonPersen = diskonPersen;
    }

    public void setKadaluarsa(LocalDate kadaluarsa) {
        this.kadaluarsa = kadaluarsa;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    
    public String getFormattedKadaluarsa() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return kadaluarsa.format(formatter);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isKadaluarsa(){
        if(kadaluarsa.isAfter(LocalDate.now())){
            return false;
        }else if (kadaluarsa.isEqual(LocalDate.now())) {
            return false;
        }else{
            return true;
        }
    }

    
}
