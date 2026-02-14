package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "account_ledgers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "code"}))
public class AccountLedger extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 20)
    private String code;                // 10100 - Cash, 20100 - Tuition Fees Income, etc.

    @NotBlank
    @Size(max = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    private LedgerGroup group;          // ASSET, LIABILITY, INCOME, EXPENSE, EQUITY

    @Enumerated(EnumType.STRING)
    private LedgerType type;            // CASH, BANK, STUDENT_RECEIVABLE, FEE_INCOME, etc.

    private Double openingBalance = 0.0;

    private LocalDate openingBalanceDate;

    private boolean isSystem = false;   // Cannot delete

    // ...
}
