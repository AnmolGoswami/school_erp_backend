package com.schoolErp.entity.document_and_certificate;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.fee_accounting_finance_module.FeePayment;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificate_requests")
public class CertificateRequest extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "user_id")               // if staff or parent requests
    private User requestedBy;

    @ManyToOne
    @JoinColumn(name = "certificate_type_id", nullable = false)
    private CertificateType certificateType;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDate requestedDate;

    private LocalDate requiredByDate;

    @Column(columnDefinition = "text")
    private String reason;

    private boolean isUrgent = false;

    private Double processingFee;               // if any

    private boolean feePaid = false;

    @ManyToOne
    @JoinColumn(name = "fee_payment_id")
    private FeePayment linkedPayment;

    @ManyToOne
    @JoinColumn(name = "generated_certificate_id")
    private GeneratedCertificate generatedCertificate;

    // ...
}
