package com.schoolErp.entity.admission_lead_crm_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.LeadSource;
import com.schoolErp.enums.LeadStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 1. Lead (Core entity - entry point from all sources)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leads",
        indexes = {
                @Index(name = "idx_lead_tenant_mobile", columnList = "tenant_id, mobile"),
                @Index(name = "idx_lead_tenant_email", columnList = "tenant_id, email"),
                @Index(name = "idx_lead_status", columnList = "status")
        })
public class Lead extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lead_seq")
    @SequenceGenerator(name = "lead_seq", sequenceName = "lead_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    // Lead source - critical for analytics & campaign ROI
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadSource source;  // WEBSITE, WALK_IN, PHONE, CAMPAIGN, REFERRAL, EXCEL_IMPORT, OTHER

    @Column(length = 50)
    private String sourceCampaign;     // e.g. "Google Ads Jan 2026", "Facebook Lead Form"

    @Column(length = 100)
    private String utmSource;

    @Column(length = 100)
    private String utmMedium;

    @Column(length = 100)
    private String utmCampaign;

    // Personal info (prospect child)
    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String studentName;

    @Column(columnDefinition = "date")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // Class / grade interested in
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interested_academic_year_id")
    private AcademicYear interestedAcademicYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interested_class_id")
    private SchoolClass interestedClass;   // Assume you have SchoolClass entity later

    // Guardian / primary contact
    @NotBlank
    @Size(max = 150)
    private String guardianName;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private String mobile;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 255)
    private String address;

    @Size(max = 100)
    private String city;

    @Size(max = 50)
    private String state;

    // Lead quality / AI fields
    @Column(columnDefinition = "integer default 0")
    private Integer leadScore;          // 0-100, populated by rule or AI

    @Column(columnDefinition = "real default 0.0")
    private Double admissionProbability; // 0.0-1.0, AI predicted

    // Current funnel stage
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadStatus status = LeadStatus.NEW_LEAD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_counselor_id")
    private User assignedCounselor;     // Teacher/Counselor user

    @Column(columnDefinition = "text")
    private String notes;

    @Column(columnDefinition = "jsonb")
    private String customFields;        // JSONB for school-specific questions (e.g. {"sibling": true, "transport_needed": "yes"})

    // Statistics / automation tracking
    private LocalDateTime lastActivityAt;

    private LocalDateTime nextFollowUpDue;

    private Integer followUpCount = 0;

    // ... getters, setters, enums
}
