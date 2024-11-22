package com.springboot.tubespbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query(value = "SELECT * FROM voucher WHERE id_customer = :id_customer", nativeQuery = true)
    List<Voucher> findByCustomerId(@Param("id_customer") Long id_customer);

}
