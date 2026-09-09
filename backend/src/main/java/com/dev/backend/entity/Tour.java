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
@Table(name = "tour",
        uniqueConstraints = @UniqueConstraint(name = "uq_tour_code", columnNames = "code"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Tour extends AuditableEntity {

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "name", length = 180, nullable = false)
    private String name;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(name = "itinerary", columnDefinition = "TEXT")
    private String itinerary;

    @Column(name = "duration_hours", nullable = false, precision = 5, scale = 2)
    private BigDecimal durationHours;

    @Column(name = "price_per_person", nullable = false, precision = 15, scale = 2)
    private BigDecimal pricePerPerson;

    @Column(name = "min_participants", nullable = false)
    @Builder.Default
    private Integer minParticipants = 1;

    @Column(name = "meeting_point", length = 255)
    private String meetingPoint;

    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;

    @Lob
    @Column(name = "safety_warning", columnDefinition = "TEXT", nullable = false)
    private String safetyWarning;

    @Lob
    @Column(name = "insurance_info", columnDefinition = "TEXT", nullable = false)
    private String insuranceInfo;

    @Lob
    @Column(name = "refund_policy", columnDefinition = "TEXT", nullable = false)
    private String refundPolicy;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AnhTour> anhs = new ArrayList<>();

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<LichKhoiHanhTour> lichKhoiHanhs = new ArrayList<>();
}
