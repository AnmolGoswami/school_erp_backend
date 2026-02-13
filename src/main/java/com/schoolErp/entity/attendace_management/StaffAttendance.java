package com.schoolErp.entity.attendace_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.AttendanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "staff_attendances",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "attendance_date"}),
        indexes = @Index(columnList = "attendance_date"))
public class StaffAttendance extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_att_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;                  // Staff member (teacher/admin/etc.)

    @NotNull
    @Column(nullable = false)
    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status = AttendanceStatus.ABSENT;

    @Size(max = 500)
    private String remarks;

    // Shift-based
    @Size(max = 50)
    private String shiftName;           // "Morning", "Evening", "General"

    private LocalTime inTime;

    private LocalTime outTime;

    private Integer lateMinutes;

    private Integer earlyLeaveMinutes;

    // Biometric
    @Size(max = 100)
    private String biometricId;

    private boolean isManual = true;

    @ManyToOne
    @JoinColumn(name = "marked_by_id")
    private User markedBy;

    // Link to leave (if ON_LEAVE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_request_id")
    private LeaveRequest leaveRequest;

    // ...
}
