package com.schoolErp.entity.examination_report_and_result;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "grading_schemes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "academic_year_id", "name"}))
public class GradingScheme extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_scheme_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @NotBlank
    @Size(max = 100)
    private String name;                // "CBSE Scholastic Grading", "Custom Division"

    @Column(columnDefinition = "jsonb")
    private String gradeRanges;         // JSONB: [{"min":91, "max":100, "grade":"A1", "point":10}, ...]

    private boolean isScholastic = true; // vs Co-scholastic

    private boolean active = true;

    // ... getters
}
