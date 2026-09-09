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
@Table(name = "danh_gia",
        uniqueConstraints = @UniqueConstraint(name = "uq_danh_gia_target",
                columnNames = {"nguoi_dung_id", "target_type", "target_id"}),
        indexes = @Index(name = "ix_danh_gia_target", columnList = "target_type, target_id, is_visible"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DanhGia extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nguoi_dung_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_dg_nguoi_dung"))
    private NguoiDung nguoiDung;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false, length = 20)
    private ReviewTargetType targetType;

    /** Da hinh: dat_phong.id | dat_tour.id | don_mon.id — khong co khoa ngoai. */
    @Column(name = "target_id", length = 36, nullable = false)
    private String targetId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Lob
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "is_visible", nullable = false)
    @Builder.Default
    private Boolean isVisible = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderated_by", foreignKey = @ForeignKey(name = "fk_dg_moderated_by"))
    private NguoiDung moderatedBy;

    @Column(name = "moderated_at")
    private LocalDateTime moderatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
