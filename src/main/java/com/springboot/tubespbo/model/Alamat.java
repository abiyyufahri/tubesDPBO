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
    
    

    public Alamat(int idPesanan, String namaJalan, String nomorRumah, String kota, String provinsi, String kodePos,
            String negara, String koordinat) {
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
        return "Alamat\n" +
                "Nama Jalan: " + namaJalan + "\n" +
                "Nomor Rumah: " + nomorRumah + "\n" +
                "Kota: " + kota + "\n" +
                "Provinsi: " + provinsi + "\n" +
                "Kode Pos: " + kodePos + "\n" +
                "Negara: " + negara + "\n";
    }
}
