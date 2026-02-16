package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "inventory_items",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "item_code"}),
                @UniqueConstraint(columnNames = {"tenant_id", "barcode"})
        },
        indexes = {
                @Index(name = "idx_item_tenant", columnList = "tenant_id"),
                @Index(name = "idx_item_category", columnList = "category_id"),
                @Index(name = "idx_item_active", columnList = "active")
        }
)
public class InventoryItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_item_seq")
    @SequenceGenerator(name = "inventory_item_seq", sequenceName = "inventory_item_seq", allocationSize = 1)
    private Long id;

    // ===============================
    // Multi-Tenant
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    // ===============================
    // Category
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategory category;

    // ===============================
    // Basic Item Info
    // ===============================

    @NotBlank
    @Size(max = 50)
    @Column(name = "item_code", nullable = false)
    private String itemCode; // SKU like ITEM-001

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 50)
    private String brand;

    @Size(max = 100)
    private String model;

    // ===============================
    // Identification
    // ===============================

    @Size(max = 100)
    private String barcode;   // EAN-13, UPC

    @Size(max = 100)
    private String rfidTag;

    @Size(max = 100)
    private String qrCode;

    // ===============================
    // Unit & Measurement
    // ===============================

    @NotBlank
    @Size(max = 50)
    private String unitOfMeasure; // PCS, KG, LITRE, BOX

    private Double conversionFactor;
    // Example: 1 BOX = 10 PCS

    // ===============================
    // Pricing
    // ===============================

    @Column(precision = 15, scale = 2)
    private BigDecimal purchasePrice;

    @Column(precision = 15, scale = 2)
    private BigDecimal sellingPrice;

    @Column(precision = 15, scale = 2)
    private BigDecimal standardCost;

    // ===============================
    // GST & Tax
    // ===============================

    @Size(max = 20)
    private String hsnCode;

    private Double gstRate;

    private boolean taxIncludedInPrice = false;

    // ===============================
    // Stock Control Settings
    // ===============================

    private Double minimumStockLevel;

    private Double reorderLevel;

    private Double maximumStockLevel;

    private boolean allowNegativeStock = false;

    private boolean batchTrackingEnabled = false;

    private boolean expiryTrackingEnabled = false;

    private boolean serialTrackingEnabled = false;

    // ===============================
    // Inventory Behavior
    // ===============================

    private boolean purchasable = true;

    private boolean sellable = false;

    private boolean assetLinked = false;
    // If true → convert to FixedAsset on purchase

    // ===============================
    // Accounting Integration (Future Ready)
    // ===============================

    @Size(max = 100)
    private String inventoryAccountCode;

    @Size(max = 100)
    private String expenseAccountCode;

    @Size(max = 100)
    private String incomeAccountCode;

    // ===============================
    // System Control
    // ===============================

    private boolean active = true;

    private boolean deleted = false; // soft delete

    @Size(max = 500)
    private String remarks;

    // ===============================
    // Custom Attributes (Flexible Metadata)
    // ===============================

    @Column(columnDefinition = "jsonb")
    private String customAttributes;
    // Example:
    // { "color": "Blue", "size": "M", "material": "Cotton" }

    // ===============================
    // Getters & Setters
    // ===============================
}

