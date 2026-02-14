package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "fee_payments")
public class FeePayment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fee_pay_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String receiptNumber;       // auto-generated e.g. REC-2025-000456

    @NotNull
    private LocalDate paymentDate;

    private Double amountPaid;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;    // CASH, UPI, CARD, BANK_TRANSFER, CHEQUE, ONLINE

    @Size(max = 100)
    private String transactionId;       // UPI ref / cheque no

    @Size(max = 500)
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "received_by_id")
    private User receivedBy;

    @OneToMany(mappedBy = "feePayment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeePaymentDetail> details = new ArrayList<>(); // which fee items paid

    private boolean isPrinted = false;

    // ...
}
