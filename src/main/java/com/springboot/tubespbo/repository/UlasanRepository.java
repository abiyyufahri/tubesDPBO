package com.springboot.tubespbo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.Ulasan;

@Repository
public interface UlasanRepository extends JpaRepository<Ulasan, Long> {
}