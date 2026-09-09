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
@Table(name = "chi_tiet_dat_phong",
        indexes = @Index(name = "ix_ctdp_phong", columnList = "phong_id, actual_check_out"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ChiTietDatPhong extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dat_phong_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ctdp_dat_phong"))
    private DatPhong datPhong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hang_phong_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ctdp_hang_phong"))
    private HangPhong hangPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phong_id", foreignKey = @ForeignKey(name = "fk_ctdp_phong"))
    private Phong phong;

    @Column(name = "rate_per_night", nullable = false, precision = 15, scale = 2)
    private BigDecimal ratePerNight;

    @Column(name = "actual_check_in")
    private LocalDateTime actualCheckIn;

    @Column(name = "actual_check_out")
    private LocalDateTime actualCheckOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checked_in_by", foreignKey = @ForeignKey(name = "fk_ctdp_checkin_by"))
    private NguoiDung checkedInBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checked_out_by", foreignKey = @ForeignKey(name = "fk_ctdp_checkout_by"))
    private NguoiDung checkedOutBy;

    @OneToMany(mappedBy = "chiTietDatPhong", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<KhaiBaoLuuTru> khaiBaos = new ArrayList<>();
}
