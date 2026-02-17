package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;

@Entity
@Table(name = "payroll_runs")
public class PayrollRun extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payroll_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @NotNull
    private YearMonth payrollMonth;     // e.g. 2026-02

    @Enumerated(EnumType.STRING)
    private PayrollStatus status = PayrollStatus.DRAFT;

    private LocalDate processedDate;

    @ManyToOne
    @JoinColumn(name = "processed_by_id")
    private User processedBy;

    private Double totalGross;

    private Double totalDeductions;

    private Double totalNet;

    // ...
}
