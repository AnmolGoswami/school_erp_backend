package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "student_fee_items")
public class StudentFeeItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fee_item_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_fee_assignment_id", nullable = false)
    private StudentFeeAssignment assignment;

    @ManyToOne
    @JoinColumn(name = "fee_head_id", nullable = false)
    private FeeHead feeHead;

    private Double originalAmount;

    private Double concessionAmount = 0.0;

    private Double payableAmount;

    private Double paidAmount = 0.0;

    private Double dueAmount;           // calculated

    private boolean isCleared = false;

    // Installment info if split
    @Enumerated(EnumType.STRING)
    private InstallmentType installmentType;

    private Integer installmentNumber;  // 1/12 for monthly

    // ...
}
