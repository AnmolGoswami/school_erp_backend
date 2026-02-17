package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "salary_structure_components")
public class SalaryStructureComponent extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sal_str_comp_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "salary_structure_id", nullable = false)
    private SalaryStructure salaryStructure;

    @ManyToOne
    @JoinColumn(name = "salary_component_id", nullable = false)
    private SalaryComponent component;

    private Double amount;

    private Double percentage;

    // ...
}
