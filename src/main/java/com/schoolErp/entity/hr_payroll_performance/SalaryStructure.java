package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "salary_structures")
public class SalaryStructure extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sal_str_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Size(max = 100)
    private String name;                // "TGT Scale", "Admin Grade A"

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Size(max = 50)
    private String designation;

    @OneToMany(mappedBy = "salaryStructure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryStructureComponent> components = new ArrayList<>();

    private Double ctc;                 // Cost to Company (calculated)

    // ...
}