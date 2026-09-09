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
@Table(name = "hoa_don",
        uniqueConstraints = @UniqueConstraint(name = "uq_hoa_don_number", columnNames = "invoice_number"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HoaDon extends BaseEntity {

    @Column(name = "invoice_number", length = 30, nullable = false)
    private String invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "so_khach_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_hd_so_khach"))
    private SoKhach soKhach;

    @Column(name = "room_subtotal", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal roomSubtotal = BigDecimal.ZERO;

    @Column(name = "fnb_subtotal", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal fnbSubtotal = BigDecimal.ZERO;

    @Column(name = "tour_subtotal", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal tourSubtotal = BigDecimal.ZERO;

    @Column(name = "service_charge_rate", nullable = false, precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal serviceChargeRate = new BigDecimal("5.00");

    @Column(name = "service_charge", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal serviceCharge = BigDecimal.ZERO;

    @Column(name = "vat_rate", nullable = false, precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal vatRate = new BigDecimal("8.00");

    @Column(name = "vat_amount", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal vatAmount = BigDecimal.ZERO;

    @Column(name = "deposit_applied", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal depositApplied = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    @Builder.Default
    private InvoiceStatus status = InvoiceStatus.DRAFT;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_by", foreignKey = @ForeignKey(name = "fk_hd_issued_by"))
    private NguoiDung issuedBy;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChiTietHoaDon> chiTiets = new ArrayList<>();
}
