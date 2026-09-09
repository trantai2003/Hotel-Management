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
@Table(name = "hang_phong",
        uniqueConstraints = @UniqueConstraint(name = "uq_hang_phong_code", columnNames = "code"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HangPhong extends AuditableEntity {

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "name", length = 120, nullable = false)
    private String name;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "base_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "max_adults", nullable = false)
    @Builder.Default
    private Integer maxAdults = 2;

    @Column(name = "max_children", nullable = false)
    @Builder.Default
    private Integer maxChildren = 1;

    @Column(name = "bed_type", length = 60)
    private String bedType;

    @Column(name = "area_sqm", precision = 6, scale = 2)
    private BigDecimal areaSqm;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "amenities", columnDefinition = "json")
    private String amenities;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @OneToMany(mappedBy = "hangPhong", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AnhHangPhong> anhs = new ArrayList<>();

    @OneToMany(mappedBy = "hangPhong", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Phong> phongs = new ArrayList<>();
}
