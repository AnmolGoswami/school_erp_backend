package com.schoolErp.entity.core;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

// 3. Role (RBAC)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;              // Roles are per-tenant (can be global too, but per-tenant is safer)

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true) // unique per tenant
    private String name;                // "ADMIN", "PRINCIPAL", "TEACHER", "ACCOUNTANT", "COUNSELOR", "STUDENT", "PARENT"

    @Size(max = 255)
    private String description;

    private boolean systemRole = false; // Cannot delete system roles

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    // ...
}
