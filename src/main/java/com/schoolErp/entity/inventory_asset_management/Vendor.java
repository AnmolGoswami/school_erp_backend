package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;

import com.schoolErp.enums.BalanceType;
import com.schoolErp.enums.VendorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "vendors",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "code"}),
                @UniqueConstraint(columnNames = {"tenant_id", "gst_number"})
        },
        indexes = {
                @Index(name = "idx_vendor_tenant", columnList = "tenant_id"),
                @Index(name = "idx_vendor_name", columnList = "name"),
                @Index(name = "idx_vendor_active", columnList = "active")
        }
)
public class Vendor extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_seq")
    @SequenceGenerator(name = "vendor_seq", sequenceName = "vendor_seq", allocationSize = 1)
    private Long id;

    // ===============================
    // Multi-Tenant
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    // ===============================
    // Basic Info
    // ===============================

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String code;   // VEND-001, SUP-2025-001

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String legalName;

    @Size(max = 255)
    private String addressLine1;

    @Size(max = 255)
    private String addressLine2;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 20)
    private String pincode;

    @Size(max = 100)
    private String country = "India";

    // ===============================
    // Contact Info
    // ===============================

    @Size(max = 100)
    private String contactPersonName;

    @Size(max = 15)
    private String phone;

    @Size(max = 15)
    private String alternatePhone;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 150)
    private String website;

    // ===============================
    // GST / Tax Details (India Ready)
    // ===============================

    @Size(max = 15)
    @Column(name = "gst_number")
    private String gstNumber;

    @Size(max = 10)
    private String panNumber;

    @Size(max = 20)
    private String tanNumber;

    private boolean gstRegistered = true;

    @Enumerated(EnumType.STRING)
    private VendorType type;

    // ===============================
    // Financial Details
    // ===============================

    @Column(precision = 15, scale = 2)
    private BigDecimal creditLimit = BigDecimal.ZERO;

    private Integer creditDays;   // Payment terms

    @Column(precision = 15, scale = 2)
    private BigDecimal openingBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private BalanceType openingBalanceType;  // DR / CR

    @Size(max = 50)
    private String bankName;

    @Size(max = 50)
    private String bankAccountNumber;

    @Size(max = 20)
    private String ifscCode;

    @Size(max = 20)
    private String upiId;

    // ===============================
    // System Control
    // ===============================

    private boolean active = true;

    private boolean blocked = false; // Prevent new PO if blocked

    @Size(max = 500)
    private String remarks;

    // ===============================
    // Soft Delete
    // ===============================

    private boolean deleted = false;

    // ===============================
    // ENUMS
    // ===============================





    // ===============================
    // Getters & Setters
    // ===============================
}
