package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.AssetStatus;
import com.schoolErp.enums.DepreciationMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "fixed_assets")
@Getter
@Setter
public class FixedAsset extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_seq")
    @SequenceGenerator(name = "asset_seq", sequenceName = "asset_seq", allocationSize = 1)
    private Long id;

    // =============================
    // Multi-Tenancy
    // =============================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    // =============================
    // Basic Details
    // =============================
    @NotBlank
    @Size(max = 100)
    private String assetName;

    @Size(max = 50)
    @Column(unique = true)
    private String assetCode;   // FA-2025-001

    @Size(max = 100)
    private String barcode;

    @Size(max = 100)
    private String rfidTag;

    // =============================
    // Category
    // =============================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private AssetCategory category;

    // =============================
    // Purchase Info
    // =============================
    private LocalDate purchaseDate;

    @Column(precision = 15, scale = 2)
    private BigDecimal purchaseCost;

    @Column(precision = 15, scale = 2)
    private BigDecimal salvageValue;

    // =============================
    // Depreciation
    // =============================
    @Enumerated(EnumType.STRING)
    private DepreciationMethod depreciationMethod = DepreciationMethod.STRAIGHT_LINE;

    @Column(precision = 5, scale = 2)
    private BigDecimal depreciationRate;   // %

    private Integer usefulLifeYears;

    @Column(precision = 15, scale = 2)
    private BigDecimal accumulatedDepreciation = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2)
    private BigDecimal currentBookValue;

    // =============================
    // Location & Assignment
    // =============================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_warehouse_id")
    private Warehouse location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_user_id")
    private User assignedTo;

    // =============================
    // Status
    // =============================
    @Enumerated(EnumType.STRING)
    private AssetStatus status = AssetStatus.ACTIVE;

    private LocalDate disposalDate;

    @Column(precision = 15, scale = 2)
    private BigDecimal disposalValue;

    // =============================
    // Warranty & AMC
    // =============================
    private LocalDate warrantyEndDate;

    private LocalDate nextAmcDueDate;

    // =============================
    // Auto Book Value Calculation
    // =============================
    @PrePersist
    @PreUpdate
    private void calculateBookValue() {
        if (purchaseCost != null && accumulatedDepreciation != null) {
            this.currentBookValue = purchaseCost.subtract(accumulatedDepreciation);
        }
    }
}
