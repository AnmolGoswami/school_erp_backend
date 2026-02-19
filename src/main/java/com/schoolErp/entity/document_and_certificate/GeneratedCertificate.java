package com.schoolErp.entity.document_and_certificate;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "generated_certificates",
        indexes = {
                @Index(columnList = "student_id, certificate_type_id"),
                @Index(columnList = "certificate_number")
        })
public class GeneratedCertificate extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_cert_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "certificate_type_id", nullable = false)
    private CertificateType certificateType;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private CertificateTemplate usedTemplate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "user_id")               // for staff certificates
    private User staff;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String certificateNumber;           // TC-2025-000123, CC/2025/456 (auto-generated per type/year)

    private LocalDate issueDate;

    private LocalDate validFrom;

    private LocalDate validTill;                // null for lifetime

    @Enumerated(EnumType.STRING)
    private CertificateStatus status = CertificateStatus.DRAFT;

    @Size(max = 100)
    private String serialNumber;                // for official registers

    @Column(columnDefinition = "text")
    private String renderedContent;             // final HTML/text before PDF

    @Size(max = 500)
    private String pdfFilePath;                 // /certificates/2025/TC-2025-000123.pdf

    private Long pdfFileSize;

    private boolean digitallySigned = false;

    @ManyToOne
    @JoinColumn(name = "signed_by_id")
    private User signedBy;

    private LocalDateTime signedAt;

    private boolean printed = false;

    private Integer printCount = 0;

    private LocalDateTime lastPrintedAt;

    @Column(columnDefinition = "jsonb")
    private String metadata;                    // {"class": "10A", "tc_reason": "parent transfer", "fee_cleared": true}

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;

    private LocalDateTime approvedAt;

    @Size(max = 500)
    private String remarks;

    // ...
}
