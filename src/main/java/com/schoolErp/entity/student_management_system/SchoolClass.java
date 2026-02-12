package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school_classes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "academic_year_id", "name"}))
public class SchoolClass extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String name;                // "Nursery", "Class 1", "Class 10", "XI Science"

    @Column(columnDefinition = "integer default 0")
    private Integer sequenceOrder;      // For sorting: 1 = Nursery, 10 = Class 10, etc.

    @Size(max = 20)
    private String shortCode;           // "NUR", "1", "10", "11S"

    private boolean active = true;

    // ... getters
}
