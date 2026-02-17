package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.enums.LeaveType;
import jakarta.persistence.*;

@Entity
@Table(name = "staff_leave_balances",
        uniqueConstraints = @UniqueConstraint(columnNames = {"staff_profile_id", "academic_year_id", "leave_type"}))
public class StaffLeaveBalance extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_bal_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_profile_id", nullable = false)
    private StaffProfile staffProfile;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private Integer openingBalance;

    private Integer earned;

    private Integer availed;

    private Integer lapsed;

    private Integer closingBalance;     // calculated

    // ... update methods on leave approval
}
