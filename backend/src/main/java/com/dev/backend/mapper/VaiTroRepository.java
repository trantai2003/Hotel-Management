package com.dev.backend.mapper;

import com.dev.backend.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, String> {
    Optional<VaiTro> findByCode(String code);
}
