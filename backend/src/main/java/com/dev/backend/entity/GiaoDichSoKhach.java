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

/**
 * Bang trung tam cua he thong — APPEND-ONLY.
 * DB co trigger chan UPDATE/DELETE, nen KHONG bao gio goi save() de sua
 * mot ban ghi da ton tai; sua sai bang cach ghi dong doi ung (reversalOf).
 */
@Entity
@Table(name = "giao_dich_so_khach",
        indexes = {
                @Index(name = "ix_gdsk_so_khach", columnList = "so_khach_id, posted_at"),
                @Index(name = "ix_gdsk_bao_cao", columnList = "business_date, transaction_type"),
                @Index(name = "ix_gdsk_source", columnList = "source_ref_type, source_ref_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GiaoDichSoKhach extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "so_khach_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_gdsk_so_khach"))
    private SoKhach soKhach;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false, length = 20)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false, length = 10)
    private TransactionDirection direction;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    /** Cot sinh trong DB: DEBIT (+) / CREDIT (-). Chi doc. */
    @Generated(event = {EventType.INSERT, EventType.UPDATE})
    @Column(name = "signed_amount", insertable = false, updatable = false, precision = 15, scale = 2)
    private BigDecimal signedAmount;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_ref_type", nullable = false, length = 20)
    private SourceRefType sourceRefType;

    @Column(name = "source_ref_id", length = 36)
    private String sourceRefId;

    @Column(name = "business_date", nullable = false)
    private LocalDate businessDate;

    @Column(name = "posted_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime postedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", foreignKey = @ForeignKey(name = "fk_gdsk_posted_by"))
    private NguoiDung postedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reversal_of_id", foreignKey = @ForeignKey(name = "fk_gdsk_reversal"))
    private GiaoDichSoKhach reversalOf;
}
