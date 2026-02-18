package com.schoolErp.entity.library_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "library_reading_analytics",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "academic_year_id"}))
public class LibraryReadingAnalytics extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analytics_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    private Integer booksIssued;

    private Integer booksReturnedOnTime;

    private Integer overdueCount;

    private Double totalFinePaid;

    private Integer totalRenewals;

    @Column(columnDefinition = "jsonb")
    private String popularGenres;       // JSONB: {"Fiction": 12, "Science": 5, ...}

    // ... updated via batch job on returns
}
