package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "fee_structure_details")
public class FeeStructureDetail extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fee_det_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_structure_id", nullable = false)
    private FeeStructure feeStructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_head_id", nullable = false)
    private FeeHead feeHead;

    @NotNull
    private Double amount;

    private boolean isOptional = false;

    private boolean isRefundable = false;

    private Double maxConcessionAllowed;    // e.g. max 50% scholarship

    // ...
}
