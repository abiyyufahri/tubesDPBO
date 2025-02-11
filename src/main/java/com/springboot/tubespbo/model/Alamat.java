package com.springboot.tubespbo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alamat")
public class Alamat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaJalan;
    
    private String nomorRumah;
    
    private String kota;
    
    private String provinsi;
    
    private String kodePos;
    
    private String negara;
    
    public Alamat(){}

    public Alamat(String namaJalan, String nomorRumah, String kota, String provinsi, String kodePos,
            String negara) {
        this.namaJalan = namaJalan;
        this.nomorRumah = nomorRumah;
        this.kota = kota;
        this.provinsi = provinsi;
        this.kodePos = kodePos;
        this.negara = negara;
    }



    public void setAlamat(String namaJalan, String nomorRumah, String kota, String provinsi, String kodePos,
            String negara, String koordinat) {
        this.namaJalan = namaJalan;
        this.nomorRumah = nomorRumah;
        this.kota = kota;
        this.provinsi = provinsi;
        this.kodePos = kodePos;
        this.negara = negara;
    }

    

    public String getAlamatLengkap(){
        return "(No. "+ nomorRumah + ") " + namaJalan + ", " + kota + ", " + provinsi + ", " + negara + ", " + kodePos;
    }

    public Long getId() {
        return id;
    }

    public String getNamaJalan() {
        return namaJalan;
    }

    public String getNomorRumah() {
        return nomorRumah;
    }

    public String getKota() {
        return kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public String getKodePos() {
        return kodePos;
    }

    public String getNegara() {
        return negara;
    }
}
