package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.fee_accounting_finance_module.StudentFeeItem;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hostel_allocations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "academic_year_id"}))
public class HostelAllocation extends AuditableEntity {

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
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private HostelRoom room;

    @ManyToOne
    @JoinColumn(name = "bed_id", nullable = false)
    private HostelBed bed;

    @Enumerated(EnumType.STRING)
    private AllocationStatus status = AllocationStatus.ACTIVE;

    private LocalDate allocationDate;

    private LocalDate checkoutDate;     // null if ongoing

    private boolean isBoarder = true;   // vs day scholar (for reporting)

    // Fee link
    @ManyToOne
    @JoinColumn(name = "hostel_fee_item_id")
    private StudentFeeItem hostelFeeItem;

    @ManyToOne
    @JoinColumn(name = "mess_fee_item_id")
    private StudentFeeItem messFeeItem;

    // ...
}
