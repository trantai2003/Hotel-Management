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
@Table(name = "ngay_lam_viec")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NgayLamViec implements Serializable {

    @Id
    @Column(name = "business_date", nullable = false)
    private LocalDate businessDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    @Builder.Default
    private BusinessDateStatus status = BusinessDateStatus.OPEN;

    @Column(name = "rooms_available", nullable = false)
    @Builder.Default
    private Integer roomsAvailable = 0;

    @Column(name = "rooms_sold", nullable = false)
    @Builder.Default
    private Integer roomsSold = 0;

    @Column(name = "occupancy_rate", nullable = false, precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal occupancyRate = BigDecimal.ZERO;

    @Column(name = "room_revenue", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal roomRevenue = BigDecimal.ZERO;

    @Column(name = "fnb_revenue", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal fnbRevenue = BigDecimal.ZERO;

    @Column(name = "tour_revenue", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal tourRevenue = BigDecimal.ZERO;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closed_by", foreignKey = @ForeignKey(name = "fk_nlv_closed_by"))
    private NguoiDung closedBy;
}
