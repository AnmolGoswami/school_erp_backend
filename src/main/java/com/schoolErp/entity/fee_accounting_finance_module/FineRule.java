package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "fine_rules")
public class FineRule extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fine_rule_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "fee_head_id")
    private FeeHead applicableFeeHead;  // or null = all

    private Integer graceDays;

    @Enumerated(EnumType.STRING)
    private FineType fineType;          // FIXED, PERCENTAGE, SLAB

    private Double fineAmount;          // fixed or %

    @Column(columnDefinition = "jsonb")
    private String slabRules;           // JSONB: [{"days":1-7, "amount":50}, {"days":8+, "amount":100}]

    private boolean active = true;

    // ...
}