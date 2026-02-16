package com.schoolErp.entity.inventory_asset_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.enums.DepreciationMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "asset_categories")
@Getter
@Setter
public class AssetCategory extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;  // Furniture, IT Equipment, Vehicle

    private String description;

    @Enumerated(EnumType.STRING)
    private DepreciationMethod defaultDepreciationMethod;

    @Column(precision = 5, scale = 2)
    private BigDecimal defaultDepreciationRate;

    private Integer defaultUsefulLifeYears;

    // Accounting mapping (optional but industry level)
    private String assetAccountCode;
    private String depreciationExpenseAccountCode;
    private String accumulatedDepreciationAccountCode;
}

