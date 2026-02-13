package com.schoolErp.entity.attendace_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.SchoolClass;
import com.schoolErp.entity.student_management_system.Section;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.AttendanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "student_attendances",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "academic_year_id", "attendance_date", "school_class_id", "section_id"}),
        indexes = {
                @Index(columnList = "attendance_date"),
                @Index(columnList = "student_id, attendance_date"),
                @Index(columnList = "school_class_id, attendance_date")
        })
public class StudentAttendance extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stu_att_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @NotNull
    @Column(nullable = false)
    private LocalDate attendanceDate;   // e.g. 2026-02-09

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;            // null if no sections

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status = AttendanceStatus.ABSENT;

    @Size(max = 500)
    private String remarks;

    // For period-wise / subject-wise (optional, can be null for daily-only mode)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;            // if period/subject attendance

    private Integer periodNumber;       // 1 to 8 for timetable slots

    // Biometric / device info
    @Size(max = 100)
    private String biometricDeviceId;   // e.g. fingerprint scanner serial

    private LocalTime markedAtTime;     // exact punch-in time

    private boolean isManualEntry = true; // false if from biometric

    @ManyToOne
    @JoinColumn(name = "marked_by_id")
    private User markedBy;              // Teacher / admin who marked

    // For analytics / late calc
    private boolean isLateArrival = false;

    // ... getters, helpers (e.g. isPresent())
}
