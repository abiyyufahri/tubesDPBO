package com.springboot.tubespbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.Alamat;

@Repository
public interface AlamatRepository extends JpaRepository<Alamat, Long> {
}
