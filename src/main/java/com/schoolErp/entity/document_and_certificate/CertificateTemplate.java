package com.schoolErp.entity.document_and_certificate;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "certificate_templates")
public class CertificateTemplate extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temp_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "certificate_type_id", nullable = false)
    private CertificateType certificateType;

    @NotBlank
    @Size(max = 100)
    private String version;             // v1.0, 2025-26

    @Column(columnDefinition = "text")
    private String content;             // HTML/Thymeleaf/FreeMarker template or path to .docx

    @Size(max = 500)
    private String filePath;            // /templates/student/tc_2025.html or docx

    private String mimeType;            // text/html, application/vnd.openxmlformats-officedocument.wordprocessingml.document

    private boolean isActive = true;

    private boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;

    private LocalDate approvedAt;

    @Column(columnDefinition = "jsonb")
    private String placeholders;        // ["student_name", "admission_number", "class_name", "tc_issue_date", ...]

    // ...
}
