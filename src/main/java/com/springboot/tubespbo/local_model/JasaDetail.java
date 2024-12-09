package com.springboot.tubespbo.local_model;

import com.springboot.tubespbo.util.CurrencyConverter;

public class JasaDetail {
    private int id;  
    private String idJenisJasa;  
    private String jenisJasa;  
    private int harga;         

    
    public JasaDetail(String idJenisJasa,String jenisJasa, int harga) {
        // this.id = id;
        this.jenisJasa = jenisJasa;
        this.idJenisJasa = idJenisJasa;
        this.harga = harga;
    }

    
    
    
    public String getJenisJasa() {
        return jenisJasa;
    }

    
    public void setJenisJasa(String jenisJasa) {
        this.jenisJasa = jenisJasa;
    }

    
    public int getHarga() {
        return harga;
    }

    
    public void setHarga(int harga) {
        this.harga = harga;
    }

    
    @Override
    public String toString() {
        return "JasaDetail{" +
               "jenisJasa='" + jenisJasa + '\'' +
               ", harga=" + harga +
               '}';
    }



    public int getId() {
        return id;
    }

    public String getIdJenisJasa() {
        return idJenisJasa;
    }

    public String formattedHarga(){
        return CurrencyConverter.toIDR(harga);
    }
}
