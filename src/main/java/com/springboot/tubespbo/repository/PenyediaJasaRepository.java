package com.springboot.tubespbo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.PenyediaJasa;

@Repository
public interface PenyediaJasaRepository extends JpaRepository<PenyediaJasa, Long> {
    Optional<PenyediaJasa> findByUsername(String username);
}
