package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fee_heads",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "code"}))
public class FeeHead extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fee_head_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String code;                // "TUTION", "ADMISSION", "TRANSPORT", "HOSTEL", "EXAM", "COMPUTER", "LIBRARY", etc.

    @NotBlank
    @Size(max = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    private FeeType feeType;            // ONE_TIME, RECURRING, OPTIONAL, REFUNDABLE_DEPOSIT

    @Enumerated(EnumType.STRING)
    private FeeFrequency frequency;     // MONTHLY, QUARTERLY, HALF_YEARLY, YEARLY, ONE_TIME

    private boolean isTaxable = false;  // GST applicable?

    @Size(max = 20)
    private String gstHsnCode;

    private Double gstPercentage;       // 18%, 12%, etc.

    @Column(columnDefinition = "text")
    private String description;

    private boolean active = true;

    // ...
}
