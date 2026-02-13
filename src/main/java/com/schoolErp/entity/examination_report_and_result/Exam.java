package com.schoolErp.entity.examination_report_and_result;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.ExamType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "exams")
public class Exam extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @NotBlank
    @Size(max = 150)
    private String name;                // "Term 1 Exam 2025-26"

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @ManyToOne
    @JoinColumn(name = "grading_scheme_id")
    private GradingScheme gradingScheme;

    @Column(columnDefinition = "date")
    private LocalDate startDate;

    @Column(columnDefinition = "date")
    private LocalDate endDate;

    private boolean isPublished = false; // Results visible?

    private boolean includeInOverall = true; // For weighted aggregate

    @Column(columnDefinition = "real default 0.0")
    private Double weightagePercentage;     // e.g. 20.0 for Unit Test

    // ...
}
