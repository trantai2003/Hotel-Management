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
@Table(name = "ton_kho_phong",
        indexes = @Index(name = "ix_ton_kho_ngay", columnList = "stay_date"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TonKhoPhong implements Serializable {

    @EmbeddedId
    @Builder.Default
    private TonKhoPhongId id = new TonKhoPhongId();

    @MapsId("hangPhongId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hang_phong_id", foreignKey = @ForeignKey(name = "fk_tkp_hang_phong"))
    private HangPhong hangPhong;

    @Column(name = "allotment", nullable = false)
    private Integer allotment;

    @Column(name = "sold", nullable = false)
    @Builder.Default
    private Integer sold = 0;
}
