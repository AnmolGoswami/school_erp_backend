package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.BusAttendanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bus_attendances",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "route_id", "attendance_date"}))
public class BusAttendance extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_att_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private TransportRoute route;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private RouteStop stop;

    @NotNull
    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    private BusAttendanceStatus status = BusAttendanceStatus.ABSENT;

    private LocalTime pickupTime;

    private LocalTime dropTime;

    private Double latitude;

    private Double longitude;           // GPS punch at pickup/drop

    @Size(max = 100)
    private String gpsDeviceId;

    @ManyToOne
    @JoinColumn(name = "marked_by_id")
    private User markedBy;              // Driver / conductor

    @Size(max = 500)
    private String remarks;

    // ...
}
