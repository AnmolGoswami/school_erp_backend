package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "fee_payment_details")
public class FeePaymentDetail extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_det_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_payment_id", nullable = false)
    private FeePayment feePayment;

    @ManyToOne
    @JoinColumn(name = "student_fee_item_id")
    private StudentFeeItem feeItem;

    private Double amountAllocated;

    // ...
}
