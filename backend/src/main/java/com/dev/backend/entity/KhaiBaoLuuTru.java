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
@Table(name = "khai_bao_luu_tru",
        indexes = @Index(name = "ix_kblt_declared", columnList = "declared_at"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class KhaiBaoLuuTru extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chi_tiet_dat_phong_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_kblt_ctdp"))
    private ChiTietDatPhong chiTietDatPhong;

    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type", nullable = false, length = 20)
    private IdType idType;

    @Column(name = "id_number_encrypted", columnDefinition = "VARBINARY(512)", nullable = false)
    private byte[] idNumberEncrypted;

    @Column(name = "nationality", length = 80, nullable = false)
    private String nationality;

    @Column(name = "permanent_address", length = 255)
    private String permanentAddress;

    @Column(name = "is_primary_guest", nullable = false)
    @Builder.Default
    private Boolean isPrimaryGuest = false;

    @Column(name = "declared_at", nullable = false)
    @Builder.Default
    private LocalDateTime declaredAt = LocalDateTime.now();

    @Column(name = "exported_at")
    private LocalDateTime exportedAt;
}
