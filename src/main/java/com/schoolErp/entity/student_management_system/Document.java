package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.admission_lead_crm_automation.AdmissionApplication;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    // Polymorphic link – use one of these
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "admission_application_id")
    private AdmissionApplication admissionApplication;

    // Add more as needed (lead, staff, asset...)

    @NotBlank
    @Size(max = 100)
    private String documentType;        // BIRTH_CERTIFICATE, AADHAAR, MARKSHEET_10, PHOTO, TC, MEDICAL_CERT, etc.

    @NotBlank
    @Size(max = 255)
    private String fileName;

    @NotBlank
    @Size(max = 500)
    private String filePath;            // e.g. /uploads/students/2025/123/photo.jpg or S3 key

    @Size(max = 50)
    private String mimeType;

    private Long fileSize;

    private LocalDate expiryDate;       // For certificates that expire

    private boolean verified = false;

    @ManyToOne
    @JoinColumn(name = "verified_by_id")
    private User verifiedBy;

    // ...
}
