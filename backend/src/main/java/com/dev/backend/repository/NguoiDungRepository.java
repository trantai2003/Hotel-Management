package com.dev.backend.repository;

import com.dev.backend.entity.NguoiDung;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepository
        extends JpaRepository<NguoiDung, String>, JpaSpecificationExecutor<NguoiDung> {

    /**
     * Nap kem vai tro trong cung mot truy van.
     * Thieu @EntityGraph se dinh LazyInitializationException khi build UserDetails.
     */
    @EntityGraph(attributePaths = {"vaiTros", "vaiTros.vaiTro"})
    Optional<NguoiDung> findByEmail(String email);

    boolean existsByEmail(String email);
}
