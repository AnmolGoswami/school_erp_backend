package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.CalculationType;
import com.schoolErp.enums.ComponentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "salary_components",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "code"}))
public class SalaryComponent extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sal_comp_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 50)
    private String code;                // BASIC, DA, HRA, TA, PF_EMPLOYEE, ESI_EMPLOYEE, TDS, PROF_TAX

    @NotBlank
    @Size(max = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private ComponentType type;         // EARNING, DEDUCTION

    @Enumerated(EnumType.STRING)
    private CalculationType calculationType; // FIXED, PERCENTAGE_OF_BASIC, FORMULA

    private Double defaultValue;

    private Double percentage;          // if percentage-based

    private boolean isStatutory = false; // PF, ESI, TDS, PT

    private boolean isTaxable = true;

    // ...
}
