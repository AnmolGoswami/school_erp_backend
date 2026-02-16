package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;


import com.schoolErp.enums.PurchaseOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "purchase_orders",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "po_number"})
        },
        indexes = {
                @Index(name = "idx_po_tenant", columnList = "tenant_id"),
                @Index(name = "idx_po_vendor", columnList = "vendor_id"),
                @Index(name = "idx_po_status", columnList = "status"),
                @Index(name = "idx_po_date", columnList = "order_date")
        }
)
public class PurchaseOrder extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "po_seq")
    @SequenceGenerator(name = "po_seq", sequenceName = "po_seq", allocationSize = 1)
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
    @Column(name = "po_number", nullable = false)
    private String poNumber;  // PO-2025-0001

    private LocalDate orderDate;

    private LocalDate expectedDeliveryDate;

    // ===============================
    // Vendor
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    // ===============================
    // Warehouse (Receiving)
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    // ===============================
    // Line Items
    // ===============================

    @OneToMany(mappedBy = "purchaseOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PurchaseOrderItem> items = new ArrayList<>();

    // ===============================
    // Financial Summary (Snapshot)
    // ===============================

    @Column(precision = 15, scale = 2)
    private BigDecimal totalTaxableAmount = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalCgstAmount = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalSgstAmount = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalIgstAmount = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // ===============================
    // Status & Workflow
    // ===============================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PurchaseOrderStatus status = PurchaseOrderStatus.DRAFT;

    private boolean approved = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private LocalDate approvedDate;

    private boolean locked = false;   // prevent editing after approval

    private boolean fullyReceived = false;

    private boolean accountingPosted = false;

    // ===============================
    // Additional Info
    // ===============================

    @Size(max = 500)
    private String remarks;

    @Size(max = 255)
    private String attachmentUrl; // quotation copy, etc.

    // ===============================
    // ENUM
    // ===============================



    // ===============================
    // Getters & Setters
    // ===============================
}

