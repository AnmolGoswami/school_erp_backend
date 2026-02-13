package com.schoolErp.entity.attendace_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "holidays",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "holiday_date"}))
public class Holiday extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holiday_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @NotNull
    private LocalDate holidayDate;

    @NotBlank
    @Size(max = 150)
    private String name;                // "Republic Day", "Diwali Break"

    @Column(columnDefinition = "text")
    private String description;

    private boolean recurringYearly = false; // e.g. Jan 26 every year

    // ...
}