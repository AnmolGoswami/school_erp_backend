package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.student_management_system.SchoolClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fee_structures")
public class FeeStructure extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fee_str_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;    // null = applicable to all classes

    @Size(max = 50)
    private String studentCategory;     // GENERAL, OBC, SC, ST, RTE, STAFF_WARD, etc. (or null = all)

    private boolean isDefault = false;  // fallback structure

    @OneToMany(mappedBy = "feeStructure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeeStructureDetail> details = new ArrayList<>();

    // ... addDetail(FeeHead, amount, isOptional, etc.)
}
