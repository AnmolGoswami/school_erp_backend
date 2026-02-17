package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "performance_appraisals")
public class PerformanceAppraisal extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appraisal_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_profile_id", nullable = false)
    private StaffProfile staffProfile;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    private LocalDate appraisalDate;

    @Enumerated(EnumType.STRING)
    private AppraisalRating rating;     // EXCELLENT, GOOD, AVERAGE, NEEDS_IMPROVEMENT

    @Column(columnDefinition = "text")
    private String strengths;

    @Column(columnDefinition = "text")
    private String areasOfImprovement;

    @Column(columnDefinition = "text")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "appraised_by_id")
    private User appraisedBy;           // Principal/HOD

    private boolean incrementRecommended = false;

    private Double recommendedIncrementPercentage;

    // ...
}
