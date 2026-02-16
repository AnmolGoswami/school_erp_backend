package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;

import com.schoolErp.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
        name = "item_categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "code"})
        },
        indexes = {
                @Index(name = "idx_category_tenant", columnList = "tenant_id"),
                @Index(name = "idx_category_parent", columnList = "parent_id"),
                @Index(name = "idx_category_active", columnList = "active")
        }
)
public class ItemCategory extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_category_seq")
    @SequenceGenerator(name = "item_category_seq", sequenceName = "item_category_seq", allocationSize = 1)
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
    // Example: CAT-STATIONERY, CAT-LAB, CAT-FURNITURE

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String name;
    // Stationery, Lab Equipment, Furniture, Uniform, Medicines

    @Size(max = 255)
    private String description;

    // ===============================
    // Hierarchy (Parent-Child)
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ItemCategory parent;

    // Example:
    // Furniture
    //    ├── Classroom Furniture
    //    ├── Office Furniture

    // ===============================
    // Category Type Control
    // ===============================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType categoryType;
    // INVENTORY / ASSET / SERVICE

    private boolean consumable = true;
    // true = stock decreases on issue
    // false = durable item

    private boolean trackBatch = false;
    // Medicines, food items

    private boolean trackExpiry = false;

    private boolean serialNumberRequired = false;
    // Computers, lab equipment

    // ===============================
    // GST Defaults (Optional)
    // ===============================

    private Double defaultGstRate;
    // Can auto-apply in InventoryItem

    @Size(max = 20)
    private String defaultHsnCode;

    // ===============================
    // Accounting Integration (Future Ready)
    // ===============================

    @Size(max = 100)
    private String expenseAccountCode;

    @Size(max = 100)
    private String assetAccountCode;

    @Size(max = 100)
    private String depreciationAccountCode;

    // ===============================
    // System Control
    // ===============================

    private boolean active = true;

    private boolean deleted = false; // soft delete

    @Size(max = 500)
    private String remarks;

    // ===============================
    // ENUMS
    // ===============================



    // ===============================
    // Getters & Setters
    // ===============================
}

