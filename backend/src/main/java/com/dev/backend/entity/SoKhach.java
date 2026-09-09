package com.dev.backend.entity;

import com.dev.backend.constant.enums.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.generator.EventType;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "so_khach",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_so_khach_number", columnNames = "folio_number"),
                @UniqueConstraint(name = "uq_so_khach_dat_phong", columnNames = "dat_phong_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SoKhach extends BaseEntity {

    @Column(name = "folio_number", length = 20, nullable = false)
    private String folioNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dat_phong_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_sk_dat_phong"))
    private DatPhong datPhong;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    @Builder.Default
    private FolioStatus status = FolioStatus.OPEN;

    @Column(name = "opened_at", nullable = false)
    @Builder.Default
    private LocalDateTime openedAt = LocalDateTime.now();

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @OneToMany(mappedBy = "soKhach", fetch = FetchType.LAZY)
    @Builder.Default
    private List<GiaoDichSoKhach> giaoDichs = new ArrayList<>();
}
