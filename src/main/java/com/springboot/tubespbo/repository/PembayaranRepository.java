package com.springboot.tubespbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.Alamat;
import com.springboot.tubespbo.model.Pembayaran;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
}
