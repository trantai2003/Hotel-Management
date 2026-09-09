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
@Table(name = "ho_so_khach",
        uniqueConstraints = @UniqueConstraint(name = "uq_ho_so_khach_nguoi_dung", columnNames = "nguoi_dung_id"),
        indexes = @Index(name = "ix_ho_so_khach_id_hash", columnList = "id_number_hash"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HoSoKhach extends AuditableEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nguoi_dung_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_hsk_nguoi_dung"))
    private NguoiDung nguoiDung;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type", nullable = false, length = 20)
    @Builder.Default
    private IdType idType = IdType.CCCD;

    @Column(name = "id_number_encrypted", columnDefinition = "VARBINARY(512)")
    private byte[] idNumberEncrypted;

    @Column(name = "id_number_hash", length = 64)
    private String idNumberHash;

    @Column(name = "id_issued_date")
    private LocalDate idIssuedDate;

    @Column(name = "id_issued_place", length = 150)
    private String idIssuedPlace;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(name = "nationality", length = 80)
    private String nationality;

    @Column(name = "permanent_address", length = 255)
    private String permanentAddress;

    @Column(name = "id_document_image_url", length = 500)
    private String idDocumentImageUrl;
}
