package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.admission_lead_crm_automation.AdmissionApplication;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.Gender;
import com.schoolErp.enums.StudentStatus;
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
@Table(name = "students",
        indexes = {
                @Index(columnList = "tenant_id, admission_number", unique = true),
                @Index(columnList = "tenant_id, roll_number")
        })
public class Student extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_application_id", unique = true)
    private AdmissionApplication admissionApplication; // Link back to funnel

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String admissionNumber;     // e.g. "SCH-2025-000123" (auto-generated)

    @Size(max = 20)
    private String rollNumber;          // e.g. "25-101" – assigned per section/year

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String fullName;

    @Column(columnDefinition = "date")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Size(max = 50)
    private String bloodGroup;

    @Size(max = 255)
    private String nationality;

    @Size(max = 50)
    private String religion;

    @Size(max = 50)
    private String category;            // General, OBC, SC, ST, EWS, etc.

    @Column(columnDefinition = "text")
    private String permanentAddress;

    @Column(columnDefinition = "text")
    private String currentAddress;

    @Size(max = 15)
    private String aadhaarNumber;       // Optional, masked in UI

    private boolean isRTE = false;      // Right to Education quota

    @Enumerated(EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;

    // Current academic allocation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_academic_year_id")
    private AcademicYear currentAcademicYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_class_id")
    private SchoolClass currentClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_section_id")
    private Section currentSection;

    // Previous class/section for promotion history
    @Column(columnDefinition = "jsonb")
    private String academicHistory;     // JSONB array of past years/classes (or separate table)

    @Column(columnDefinition = "jsonb")
    private String customFields;        // School-specific extras

    // ... getters, business methods (e.g. assignRollNumber())
}