package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "accounting_entries")
public class AccountingEntry extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private AccountingTransaction transaction;

    @ManyToOne
    @JoinColumn(name = "ledger_id", nullable = false)
    private AccountLedger ledger;

    private Double debitAmount = 0.0;

    private Double creditAmount = 0.0;

    @Size(max = 500)
    private String particulars;

    // ... validation: exactly one of debit/credit non-zero
}
