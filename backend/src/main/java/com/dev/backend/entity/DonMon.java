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
@Table(name = "don_mon",
        uniqueConstraints = @UniqueConstraint(name = "uq_don_mon_code", columnNames = "order_code"),
        indexes = {
                @Index(name = "ix_don_mon_kitchen", columnList = "status, ordered_at"),
                @Index(name = "ix_don_mon_dat_phong", columnList = "dat_phong_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DonMon extends BaseEntity {

    @Column(name = "order_code", length = 20, nullable = false)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false, length = 20)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dat_phong_id", foreignKey = @ForeignKey(name = "fk_dm_dat_phong"))
    private DatPhong datPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ban_an_id", foreignKey = @ForeignKey(name = "fk_dm_ban_an"))
    private BanAn banAn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_dung_id", foreignKey = @ForeignKey(name = "fk_dm_nguoi_dung"))
    private NguoiDung nguoiDung;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "charge_to_room", nullable = false)
    @Builder.Default
    private Boolean chargeToRoom = false;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "note", length = 300)
    private String note;

    @Column(name = "ordered_at", nullable = false)
    @Builder.Default
    private LocalDateTime orderedAt = LocalDateTime.now();

    @Column(name = "served_at")
    private LocalDateTime servedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handled_by", foreignKey = @ForeignKey(name = "fk_dm_handled_by"))
    private NguoiDung handledBy;

    @OneToMany(mappedBy = "donMon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChiTietDonMon> chiTiets = new ArrayList<>();

    @OneToMany(mappedBy = "donMon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<LichSuTrangThaiDon> lichSuTrangThais = new ArrayList<>();
}
