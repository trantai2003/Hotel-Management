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
@Table(name = "dat_tour",
        uniqueConstraints = @UniqueConstraint(name = "uq_dat_tour_code", columnNames = "booking_code"),
        indexes = {
                @Index(name = "ix_dat_tour_lkht", columnList = "lich_khoi_hanh_id, status"),
                @Index(name = "ix_dat_tour_dat_phong", columnList = "dat_phong_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DatTour extends BaseEntity {

    @Column(name = "booking_code", length = 20, nullable = false)
    private String bookingCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lich_khoi_hanh_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_dt_lkht"))
    private LichKhoiHanhTour lichKhoiHanh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_dung_id", foreignKey = @ForeignKey(name = "fk_dt_nguoi_dung"))
    private NguoiDung nguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dat_phong_id", foreignKey = @ForeignKey(name = "fk_dt_dat_phong"))
    private DatPhong datPhong;

    @Column(name = "contact_name", length = 150, nullable = false)
    private String contactName;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "num_participants", nullable = false)
    private Integer numParticipants;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    /** Cot sinh trong DB: num_participants * unit_price. Chi doc. */
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @Column(name = "total_amount", insertable = false, updatable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "charge_to_room", nullable = false)
    @Builder.Default
    private Boolean chargeToRoom = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private TourBookingStatus status = TourBookingStatus.PENDING;

    @Column(name = "qr_ticket_code", length = 100)
    private String qrTicketCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;
}
