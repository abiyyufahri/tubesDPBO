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
public class RiwayatPesanan implements IPesananType {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_temp_chat_room")
    private TempChatRoom tempChatRoom;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "id_ulasan")
    private Ulasan ulasan;

    @NotNull
    private LocalDateTime createdAt;

    public RiwayatPesanan(){}

    public RiwayatPesanan(@NotBlank String jenisJasa, @NotBlank int status, @NotNull LocalDateTime waktu,
            Pembayaran pembayaran, @NotNull Customer customer) {
        this.jenisJasa = jenisJasa;
        this.status = status;
        this.waktu = waktu;
        this.pembayaran = pembayaran;
        this.customer = customer;
        this.createdAt = LocalDateTime.now();
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
        switch (this.jenisJasa) {
            case "1":
                return JASA_PEMBERSIH;
            case "2":
                return JASA_CAT;
            case "3":
                return JASA_SERVIS;
            case "4":
                return JASA_PERBAIKI_TV;
            case "5":
                return JASA_PERBAIKI_PERABOT;
            case "6":
                return JASA_PEMOTONG_RUMPUT;
            default:
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TempChatRoom getTempChatRoom() {
        return tempChatRoom;
    }

    public void setTempChatRoom(TempChatRoom tempChatRoom) {
        this.tempChatRoom = tempChatRoom;
    }    
    
}
