package com.schoolErp.entity.fee_accounting_finance_module;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounting_transactions")
public class AccountingTransaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotBlank
    private String voucherNumber;

    @NotNull
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;       // RECEIPT, PAYMENT, JOURNAL, CONTRA

    @Size(max = 500)
    private String narration;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountingEntry> entries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fee_payment_id")
    private FeePayment feePayment;      // link if fee receipt

    // ...
}
