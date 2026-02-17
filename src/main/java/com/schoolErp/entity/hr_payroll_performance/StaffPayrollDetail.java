package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "staff_payroll_details")
public class StaffPayrollDetail extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_det_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payroll_run_id", nullable = false)
    private PayrollRun payrollRun;

    @ManyToOne
    @JoinColumn(name = "staff_profile_id", nullable = false)
    private StaffProfile staffProfile;

    @ManyToOne
    @JoinColumn(name = "staff_salary_assignment_id")
    private StaffSalaryAssignment salaryAssignment;

    private Double daysPresent;         // from attendance

    private Double payableDays;

    private Double grossEarnings;

    private Double totalDeductions;

    private Double netPay;

    @Column(columnDefinition = "jsonb")
    private String componentBreakup;    // JSONB: { "BASIC": 25000, "HRA": 8000, "PF": -1800, ... }

    private Double pfEmployeeContribution;

    private Double pfEmployerContribution;

    private Double esiEmployee;

    private Double esiEmployer;

    private Double tdsDeducted;

    private Double professionalTax;

    private boolean payslipGenerated = false;

    private String payslipFilePath;     // PDF path

    // ...
}