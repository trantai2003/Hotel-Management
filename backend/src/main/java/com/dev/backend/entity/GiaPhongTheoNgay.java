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
@Table(name = "gia_phong_theo_ngay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiaPhongTheoNgay implements Serializable {

    @EmbeddedId
    @Builder.Default
    private GiaPhongTheoNgayId id = new GiaPhongTheoNgayId();

    @MapsId("hangPhongId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hang_phong_id", foreignKey = @ForeignKey(name = "fk_gptn_hang_phong"))
    private HangPhong hangPhong;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;
}
