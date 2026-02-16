package com.schoolErp.entity.inventory_asset_management;

package com.schoolErp.entity.inventory;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;

import com.schoolErp.enums.WarehouseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
        name = "warehouses",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "code"})
        },
        indexes = {
                @Index(name = "idx_wh_tenant", columnList = "tenant_id"),
                @Index(name = "idx_wh_parent", columnList = "parent_id"),
                @Index(name = "idx_wh_active", columnList = "active")
        }
)
public class Warehouse extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_seq")
    @SequenceGenerator(name = "warehouse_seq", sequenceName = "warehouse_seq", allocationSize = 1)
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
    private String code;
    // WH-MAIN, WH-LAB, WH-HOSTEL-01

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String name;
    // Main Store, Science Lab Store, Transport Dept Store

    @Size(max = 255)
    private String description;

    // ===============================
    // Hierarchy (Parent-Child)
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Warehouse parent;
    // Example:
    // Main Store
    //    ├── Lab Section
    //    ├── Stationery Section

    // ===============================
    // Location Info
    // ===============================

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

    @Size(max = 255)
    private String locationDescription;
    // Building A - Ground Floor - Room 101

    // ===============================
    // Warehouse Type
    // ===============================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WarehouseType warehouseType;
    // MAIN, SUB, LAB, HOSTEL, TRANSPORT, CANTEEN

    private boolean isMainWarehouse = false;

    // ===============================
    // Control Settings
    // ===============================

    private boolean allowNegativeStock = false;

    private boolean approvalRequiredForIssue = false;

    private boolean approvalRequiredForTransfer = false;

    // ===============================
    // Accounting Integration (Future)
    // ===============================

    @Size(max = 100)
    private String inventoryAccountCode;

    @Size(max = 100)
    private String costCenterCode;
    // Example: LAB, TRANSPORT, HOSTEL

    // ===============================
    // System Control
    // ===============================

    private boolean active = true;

    private boolean deleted = false; // soft delete

    @Size(max = 500)
    private String remarks;

    // ===============================
    // ENUM
    // ===============================



    // ===============================
    // Getters & Setters
    // ===============================
}

