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
@Table(name = "thanh_toan",
        uniqueConstraints = @UniqueConstraint(name = "uq_thanh_toan_code", columnNames = "payment_code"),
        indexes = @Index(name = "ix_thanh_toan_gateway", columnList = "gateway_txn_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ThanhToan extends BaseEntity {

    @Column(name = "payment_code", length = 30, nullable = false)
    private String paymentCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_scope", nullable = false, length = 20)
    private PaymentScope paymentScope;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dat_phong_id", foreignKey = @ForeignKey(name = "fk_tt_dat_phong"))
    private DatPhong datPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoa_don_id", foreignKey = @ForeignKey(name = "fk_tt_hoa_don"))
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dat_tour_id", foreignKey = @ForeignKey(name = "fk_tt_dat_tour"))
    private DatTour datTour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "don_mon_id", foreignKey = @ForeignKey(name = "fk_tt_don_mon"))
    private DonMon donMon;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false, length = 20)
    private PaymentMethod method;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", length = 3, nullable = false)
    @Builder.Default
    private String currency = "VND";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private PaymentStatus status = PaymentStatus.INITIATED;

    @Column(name = "gateway_txn_id", length = 100)
    private String gatewayTxnId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "gateway_response", columnDefinition = "json")
    private String gatewayResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refund_of_id", foreignKey = @ForeignKey(name = "fk_tt_refund_of"))
    private ThanhToan refundOf;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
