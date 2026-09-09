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
@Table(name = "nguoi_dung",
        uniqueConstraints = @UniqueConstraint(name = "uq_nguoi_dung_email", columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NguoiDung extends AuditableEntity {

    @Column(name = "email", length = 190, nullable = false)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "full_name", length = 150, nullable = false)
    private String fullName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    private UserStatus status = UserStatus.PENDING_VERIFICATION;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "verification_token", length = 100)
    private String verificationToken;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "anonymized_at")
    private LocalDateTime anonymizedAt;

    @OneToOne(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private HoSoKhach hoSoKhach;

    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<NguoiDungVaiTro> vaiTros = new ArrayList<>();

    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    @Builder.Default
    private List<DongYDuLieu> dongYDuLieus = new ArrayList<>();
}
