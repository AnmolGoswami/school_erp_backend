package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "departments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "code"}))
public class Department extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String code;                // TCH, ADM, ACC, LIB, TRANS, HOSTEL, MAINT

    @NotBlank
    @Size(max = 100)
    private String name;                // Teaching, Administration, Accounts, Library, Transport, Hostel, Maintenance

    @Size(max = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "head_of_department_id")
    private User headOfDepartment;      // usually a senior staff user

    private boolean active = true;

    // ...
}
