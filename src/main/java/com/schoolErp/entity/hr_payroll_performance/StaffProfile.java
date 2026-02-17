package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.EmploymentType;
import com.schoolErp.enums.StaffCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "staff_profiles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
public class StaffProfile extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;                  // Core user account (username, name, email, phone, roles)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Size(max = 50)
    private String employeeCode;        // EMP-2025-00123 (auto-generated)

    @Column(columnDefinition = "date")
    private LocalDate dateOfJoining;

    @Column(columnDefinition = "date")
    private LocalDate dateOfResignation;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType = EmploymentType.PERMANENT;

    @Enumerated(EnumType.STRING)
    private StaffCategory staffCategory; // TEACHING, NON_TEACHING, ADMINISTRATIVE, SUPPORT

    @Size(max = 50)
    private String designation;         // Principal, TGT Maths, Accountant, Bus Driver

    @Size(max = 20)
    private String pfAccountNumber;

    @Size(max = 20)
    private String esiNumber;

    @Size(max = 20)
    private String panNumber;

    @Size(max = 20)
    private String aadhaarNumber;       // masked in UI

    @Size(max = 50)
    private String bankAccountNumber;

    @Size(max = 20)
    private String ifscCode;

    @Size(max = 100)
    private String bankName;

    private boolean isPfApplicable = true;

    private boolean isEsiApplicable = true;

    private boolean isTdsApplicable = true;

    @Column(columnDefinition = "jsonb")
    private String documents;           // JSONB array of uploaded doc types (offer letter, experience cert, etc.)

    // ...
}
