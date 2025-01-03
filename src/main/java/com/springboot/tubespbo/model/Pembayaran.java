package com.springboot.tubespbo.model;

import java.util.List;

import com.springboot.tubespbo.util.CurrencyConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int jumlah;

    @OneToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pembayaran", fetch = FetchType.EAGER)
    private RiwayatPesanan pesanan;

    @NotNull
    private String jenisPembayaran;

    public Pembayaran(){}

    public Pembayaran(@NotNull int jumlah,@NotNull String jenisPembayaran) {
        this.jumlah = jumlah;
        this.jenisPembayaran = jenisPembayaran;
    }

    public Long getId() {
        return id;
    }

    public double getJumlah() {
        if(voucher != null){
            return jumlah - (jumlah * (voucher.getDiskonPersen()/100));
        }
        return jumlah;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public String getJumlahInIDR() {
        return CurrencyConverter.toIDR(getJumlah());
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }
}
