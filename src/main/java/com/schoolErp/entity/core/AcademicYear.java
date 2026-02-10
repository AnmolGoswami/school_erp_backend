package com.schoolErp.entity.core;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 2. AcademicYear (very important engine)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "academic_years",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "year_name"}))
public class AcademicYear extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ay_seq")
    @SequenceGenerator(name = "ay_seq", sequenceName = "academic_year_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String yearName;            // "2025-2026"

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate endDate;

    private boolean current = false;    // Only one per tenant can be true

    private boolean closed = false;

    // Getters...
}
