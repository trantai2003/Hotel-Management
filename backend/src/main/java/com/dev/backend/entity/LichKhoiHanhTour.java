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
@Table(name = "lich_khoi_hanh_tour",
        uniqueConstraints = @UniqueConstraint(name = "uq_lkht_tour_date",
                columnNames = {"tour_id", "departure_date", "departure_time"}),
        indexes = @Index(name = "ix_lkht_guide_ngay", columnList = "guide_id, departure_date"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LichKhoiHanhTour extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lkht_tour"))
    private Tour tour;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "booked_seats", nullable = false)
    @Builder.Default
    private Integer bookedSeats = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id", foreignKey = @ForeignKey(name = "fk_lkht_guide"))
    private NguoiDung guide;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private TourScheduleStatus status = TourScheduleStatus.SCHEDULED;

    @OneToMany(mappedBy = "lichKhoiHanh", fetch = FetchType.LAZY)
    @Builder.Default
    private List<DatTour> datTours = new ArrayList<>();
}
