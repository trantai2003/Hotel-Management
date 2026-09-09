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
@Table(name = "dat_phong",
        uniqueConstraints = @UniqueConstraint(name = "uq_dat_phong_code", columnNames = "booking_code"),
        indexes = {
                @Index(name = "ix_dat_phong_arrival", columnList = "check_in_date, status"),
                @Index(name = "ix_dat_phong_departure", columnList = "check_out_date, status"),
                @Index(name = "ix_dat_phong_nguoi_dung", columnList = "nguoi_dung_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DatPhong extends AuditableEntity {

    @Column(name = "booking_code", length = 20, nullable = false)
    private String bookingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_dung_id", foreignKey = @ForeignKey(name = "fk_dp_nguoi_dung"))
    private NguoiDung nguoiDung;

    @Column(name = "contact_name", length = 150, nullable = false)
    private String contactName;

    @Column(name = "contact_email", length = 190)
    private String contactEmail;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "num_adults", nullable = false)
    @Builder.Default
    private Integer numAdults = 1;

    @Column(name = "num_children", nullable = false)
    @Builder.Default
    private Integer numChildren = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    @Column(name = "deposit_amount", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal depositAmount = BigDecimal.ZERO;

    @Column(name = "estimated_total", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal estimatedTotal = BigDecimal.ZERO;

    @Column(name = "special_request", length = 500)
    private String specialRequest;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Column(name = "refund_amount", precision = 15, scale = 2)
    private BigDecimal refundAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", foreignKey = @ForeignKey(name = "fk_dp_created_by"))
    private NguoiDung createdBy;

    @OneToMany(mappedBy = "datPhong", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChiTietDatPhong> chiTiets = new ArrayList<>();

    @OneToOne(mappedBy = "datPhong", fetch = FetchType.LAZY)
    private SoKhach soKhach;
}
