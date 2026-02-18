package com.schoolErp.entity.library_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;

@Entity
@Table(name = "library_fine_rules")
public class FineRule extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fine_rule_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    private Integer graceDays = 0;

    private Double finePerDay;

    private Double maxFinePerBook;

    private Double damageFinePercentage; // % of book price

    private boolean active = true;

    // ...
}
