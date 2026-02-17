package com.schoolErp.entity.hr_payroll_performance;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import jakarta.persistence.*;

@Entity
@Table(name = "staff_salary_assignments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"staff_profile_id", "academic_year_id"}))
public class StaffSalaryAssignment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sal_assign_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_profile_id", nullable = false)
    private StaffProfile staffProfile;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "salary_structure_id")
    private SalaryStructure baseStructure;

    @OneToMany(mappedBy = "staffSalaryAssignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffSalaryComponent> components = new ArrayList<>();

    private Double grossSalary;

    private Double netSalary;           // after deductions

    private boolean active = true;

    // ... copy from structure + custom overrides
}
