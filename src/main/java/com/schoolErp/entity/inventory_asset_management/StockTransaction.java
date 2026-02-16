package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;


import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "stock_transactions",
        indexes = {
                @Index(name = "idx_st_tenant", columnList = "tenant_id"),
                @Index(name = "idx_st_item", columnList = "item_id"),
                @Index(name = "idx_st_date", columnList = "transactionDate"),
                @Index(name = "idx_st_type", columnList = "transactionType")
        }
)
public class StockTransaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_tx_seq")
    @SequenceGenerator(name = "stock_tx_seq", sequenceName = "stock_tx_seq", allocationSize = 1)
    private Long id;

    // ===============================
    // Multi-Tenant
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    // ===============================
    // Item Reference
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private InventoryItem item;

    // ===============================
    // Transaction Type
    // ===============================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    // ===============================
    // Warehouse Movement
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_warehouse_id")
    private Warehouse fromWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_warehouse_id")
    private Warehouse toWarehouse;

    // ===============================
    // Quantity & Pricing
    // ===============================

    @NotNull
    private Double quantity;

    @Column(precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount;

    // ===============================
    // Batch / Serial / Expiry
    // ===============================

    @Size(max = 100)
    private String batchNumber;

    private LocalDate expiryDate;

    @Size(max = 100)
    private String serialNumber;

    // ===============================
    // Purchase & Invoice Reference
    // ===============================

    @Size(max = 100)
    private String vendorInvoiceNumber;

    private LocalDate vendorInvoiceDate;

    @Size(max = 255)
    private String invoiceDocumentUrl;

    @Size(max = 100)
    private String referenceNumber;
    // PO number / GRN number / manual reference

    // ===============================
    // Issued To (Integration)
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_to_user_id")
    private User issuedToUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_to_student_id")
    private Student issuedToStudent;

    // ===============================
    // Approval & Control
    // ===============================

    private boolean approved = false;

    private boolean posted = false;
    // once posted → cannot edit

    private LocalDate transactionDate;

    @Size(max = 500)
    private String remarks;

    // ===============================
    // ENUM
    // ===============================



    // ===============================
    // Getters & Setters
    // ===============================
}

