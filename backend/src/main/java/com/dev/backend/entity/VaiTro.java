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
@Table(name = "vai_tro",
        uniqueConstraints = @UniqueConstraint(name = "uq_vai_tro_code", columnNames = "code"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VaiTro extends BaseEntity {

    @Column(name = "code", length = 30, nullable = false)
    private String code;

    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "vaiTro", fetch = FetchType.LAZY)
    @Builder.Default
    private List<NguoiDungVaiTro> nguoiDungs = new ArrayList<>();
}
