package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import jakarta.persistence.*;

@Entity
@Table(name = "student_subject_enrollments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject_id", "academic_year_id"}))
public class StudentSubjectEnrollment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enroll_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;            // Assume Subject entity exists (name, code, type: core/elective)

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    private boolean isOptional = false;

    // ...
}
