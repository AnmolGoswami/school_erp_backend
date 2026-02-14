package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "student_fee_assignments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "academic_year_id"}))
public class StudentFeeAssignment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fee_assign_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "fee_structure_id")
    private FeeStructure baseFeeStructure;

    @OneToMany(mappedBy = "studentFeeAssignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFeeItem> feeItems = new ArrayList<>();

    private Double totalPayable;        // Calculated

    private Double totalConcession;

    private Double netPayable;

    // ... methods to copy from structure + apply concessions
}
