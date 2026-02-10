package com.schoolErp.entity.core;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

// 1. Tenant (School or Institution) - Root of multi-tenancy
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tenants",
        uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
@Audited  // If using Hibernate Envers for audit logs
public class Tenant extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_seq")
    @SequenceGenerator(name = "tenant_seq", sequenceName = "tenant_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;                // e.g. "Delhi Public School"

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true)
    private String code;                // Unique short code e.g. "DPS-Noida"

    @Size(max = 255)
    private String address;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String state;

    @Size(max = 10)
    private String pinCode;

    @Size(max = 100)
    private String affiliationNumber;   // CBSE / ICSE etc.

    @Size(max = 100)
    private String contactEmail;

    @Size(max = 15)
    private String contactPhone;

    private boolean active = true;

    @Column(columnDefinition = "jsonb")
    private String customAttributes;    // JSONB for future custom fields per school

    // Getters, setters, constructors...
}
