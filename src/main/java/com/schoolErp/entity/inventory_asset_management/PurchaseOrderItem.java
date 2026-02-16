package com.schoolErp.entity.inventory_asset_management;



import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "purchase_order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===============================
    // 🔗 Many Items belong to One Purchase Order
    // ===============================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    // ===============================
    // 🔗 Item being purchased
    // ===============================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    // ===============================
    // 📦 Quantity Ordered
    // ===============================
    @Column(nullable = false)
    private Integer quantity;

    // ===============================
    // 💰 Price Per Unit
    // ===============================
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;

    // ===============================
    // 💵 Total Amount (quantity * unitPrice)
    // ===============================
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal totalAmount;

    // ===============================
    // 📝 Optional Description
    // ===============================
    @Column(length = 500)
    private String remarks;

    // ===============================
    // 🔄 Auto calculate total before save
    // ===============================
    @PrePersist
    @PreUpdate
    private void calculateTotal() {
        if (quantity != null && unitPrice != null) {
            this.totalAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}

