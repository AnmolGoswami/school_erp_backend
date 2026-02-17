package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.LeaveType;
import jakarta.persistence.*;

@Entity
@Table(name = "leave_policies")
public class LeavePolicy extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_pol_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;        // CASUAL, SICK, EARNED, MATERNITY, etc. (from Module 4)

    private Integer maxDaysPerYear;

    private Integer maxDaysAtOnce;

    private boolean requiresMedicalCertificate = false;

    private boolean encashable = false;

    private boolean carryForward = false;

    private Integer maxCarryForwardDays;

    private boolean applicableToTeaching = true;

    private boolean applicableToNonTeaching = true;

    // ...
}
