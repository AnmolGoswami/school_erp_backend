package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.fee_accounting_finance_module.StudentFeeItem;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.TransportDirection;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_transport_allocations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "academic_year_id"}))
public class StudentTransportAllocation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alloc_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private TransportRoute route;

    @ManyToOne
    @JoinColumn(name = "stop_id", nullable = false)
    private RouteStop stop;

    @Enumerated(EnumType.STRING)
    private TransportDirection direction = TransportDirection.BOTH;

    private boolean isActive = true;

    private LocalDate startDate;

    private LocalDate endDate;          // null = ongoing

    @Column(columnDefinition = "real default 0.0")
    private Double monthlyFee;          // Can override route base fee

    private boolean feeWaived = false;

    // Link to fee module
    @ManyToOne
    @JoinColumn(name = "fee_item_id")
    private StudentFeeItem linkedFeeItem;

    // ...
}
