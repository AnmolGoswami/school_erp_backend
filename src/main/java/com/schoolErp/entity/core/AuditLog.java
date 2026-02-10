package com.schoolErp.entity.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

// 6. AuditLog (Who changed what - very important for compliance)
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User performedBy;

    @NotNull
    private LocalDateTime timestamp = LocalDateTime.now();

    @NotBlank
    private String entityType;          // "Student", "FeePayment", etc.

    private Long entityId;

    @NotBlank
    private String action;              // CREATE, UPDATE, DELETE, APPROVE...

    @Column(columnDefinition = "jsonb")
    private String oldValue;

    @Column(columnDefinition = "jsonb")
    private String newValue;

    @Size(max = 500)
    private String remark;

    private String ipAddress;

    // ...
}
