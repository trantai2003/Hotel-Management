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
@Table(name = "phong",
        uniqueConstraints = @UniqueConstraint(name = "uq_phong_number", columnNames = "room_number"),
        indexes = @Index(name = "ix_phong_hang_trang_thai",
                columnList = "hang_phong_id, occupancy_status, housekeeping_status"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Phong extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hang_phong_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_phong_hang_phong"))
    private HangPhong hangPhong;

    @Column(name = "room_number", length = 10, nullable = false)
    private String roomNumber;

    @Column(name = "floor_no", nullable = false)
    private Integer floorNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "occupancy_status", nullable = false, length = 20)
    @Builder.Default
    private OccupancyStatus occupancyStatus = OccupancyStatus.VACANT;

    @Enumerated(EnumType.STRING)
    @Column(name = "housekeeping_status", nullable = false, length = 20)
    @Builder.Default
    private HousekeepingStatus housekeepingStatus = HousekeepingStatus.CLEAN;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_status", nullable = false, length = 20)
    @Builder.Default
    private RoomServiceStatus serviceStatus = RoomServiceStatus.IN_SERVICE;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    void touch() {
        this.updatedAt = LocalDateTime.now();
    }
}
