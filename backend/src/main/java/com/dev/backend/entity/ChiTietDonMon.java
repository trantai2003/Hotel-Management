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
@Table(name = "chi_tiet_don_mon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ChiTietDonMon extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "don_mon_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ctdm_don_mon"))
    private DonMon donMon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mon_an_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ctdm_mon_an"))
    private MonAn monAn;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "note", length = 200)
    private String note;

    /** Cot sinh trong DB: quantity * unit_price. Chi doc. */
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @Column(name = "line_total", insertable = false, updatable = false, precision = 15, scale = 2)
    private BigDecimal lineTotal;
}
