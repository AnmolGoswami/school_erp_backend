package com.schoolErp.entity.document_and_certificate;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.CertificateCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "certificate_types",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "code"}))
public class CertificateType extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cert_type_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String code;                // TC, BONAFIDE, CHARACTER, MARKSHEET, ID_CARD, FEE_RECEIPT, CONDUCT_CERT, MIGRATION_CERT

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private CertificateCategory category; // STUDENT, STAFF, GENERAL

    private boolean requiresApproval = true;

    private boolean isBulkGeneratable = false; // e.g. report cards for class

    private boolean isTemplateBased = true;

    private String defaultTemplatePath;     // e.g. /templates/tc.docx or HTML template

    private boolean active = true;

    // ...
}
