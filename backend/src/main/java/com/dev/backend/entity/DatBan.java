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
@Table(name = "dat_ban",
        uniqueConstraints = @UniqueConstraint(name = "uq_dat_ban_slot",
                columnNames = {"ban_an_id", "reservation_date", "slot_start", "active_slot"}),
        indexes = @Index(name = "ix_dat_ban_ngay", columnList = "reservation_date, slot_start"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DatBan extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_dung_id", foreignKey = @ForeignKey(name = "fk_db_nguoi_dung"))
    private NguoiDung nguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dat_phong_id", foreignKey = @ForeignKey(name = "fk_db_dat_phong"))
    private DatPhong datPhong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ban_an_id", nullable = false, foreignKey = @ForeignKey(name = "fk_db_ban_an"))
    private BanAn banAn;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "slot_start", nullable = false)
    private LocalTime slotStart;

    @Column(name = "slot_end", nullable = false)
    private LocalTime slotEnd;

    @Column(name = "party_size", nullable = false)
    private Integer partySize;

    @Column(name = "contact_name", length = 150, nullable = false)
    private String contactName;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private TableReservationStatus status = TableReservationStatus.CONFIRMED;

    @Column(name = "note", length = 300)
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Cot sinh trong DB: NULL khi don da huy, dung cho UNIQUE index. Chi doc. */
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @Column(name = "active_slot", length = 1, insertable = false, updatable = false)
    private String activeSlot;
}
