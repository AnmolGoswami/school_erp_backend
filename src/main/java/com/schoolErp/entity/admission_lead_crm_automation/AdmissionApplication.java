package com.schoolErp.entity.admission_lead_crm_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 4. AdmissionApplication (After offer → formal step, links to documents)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admission_applications")
public class AdmissionApplication extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false, unique = true)
    private Lead lead;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass appliedClass;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.DRAFT;

    private LocalDate applicationDate;

    private LocalDate testDate;

    private Integer entranceTestScore;

    private String offerLetterNumber;

    private LocalDate offerDate;

    private LocalDate acceptanceDueDate;

    // Link to final Student once admitted
    @OneToOne(mappedBy = "admissionApplication")
    private Student student;            // Null until converted

    @Column(columnDefinition = "jsonb")
    private String applicationAnswers;  // JSONB - answers to dynamic form questions

    // ...
}
