package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "hostel_attendances",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "attendance_date"}))
public class HostelAttendance extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "h_att_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "allocation_id")
    private HostelAllocation allocation;

    @NotNull
    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    private HostelAttendanceStatus eveningStatus = HostelAttendanceStatus.ABSENT;

    private LocalTime eveningCheckInTime;

    @Enumerated(EnumType.STRING)
    private HostelAttendanceStatus morningStatus = HostelAttendanceStatus.ABSENT;

    private LocalTime morningCheckOutTime;

    @Size(max = 500)
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "marked_by_id")
    private User markedBy;              // Warden / staff

    // Late / early exit tracking
    private boolean isLateEntry = false;

    private boolean isEarlyExit = false;

    // ...
}
