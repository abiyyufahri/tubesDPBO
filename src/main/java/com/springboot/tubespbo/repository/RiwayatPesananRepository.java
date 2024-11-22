package com.springboot.tubespbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.Voucher;

@Repository
public interface RiwayatPesananRepository extends JpaRepository<RiwayatPesanan, Long> {
    @Query(value = "SELECT * FROM riwayat_pesanan WHERE id_customer = :id_customer", nativeQuery = true)
    List<RiwayatPesanan> findByCustomerId(@Param("id_customer") Long id_customer);

    @Query(value = "SELECT * FROM riwayat_pesanan WHERE id_customer = :id_customer AND status = :status", nativeQuery = true)
    List<RiwayatPesanan> findByCustomerIdAndStatus(@Param("id_customer") Long id_customer,@Param("status") int status);

    @Query(value = "SELECT * FROM riwayat_pesanan WHERE jenis_jasa = :jenis_jasa AND status = 1", nativeQuery = true)
    List<RiwayatPesanan> findByJenisJasa(@Param("jenis_jasa") String jenis_jasa);

    @Query(value = "SELECT * FROM riwayat_pesanan WHERE jenis_jasa = :jenis_jasa AND status = 2 AND id_penyedia_jasa = :id_penyedia_jasa", nativeQuery = true)
    RiwayatPesanan findBySedangDiambil(@Param("jenis_jasa") String jenis_jasa,@Param("id_penyedia_jasa") Long id_penyedia_jasa);

}
