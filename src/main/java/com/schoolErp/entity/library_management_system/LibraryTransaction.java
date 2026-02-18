package com.schoolErp.entity.library_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "library_transactions",
        indexes = @Index(columnList = "issue_date, due_date"))
public class LibraryTransaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "book_copy_id", nullable = false)
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;            // null if staff

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User staffMember;           // null if student

    @Enumerated(EnumType.STRING)
    private TransactionType type;       // ISSUE, RETURN, RENEWAL

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate dueDate;

    private LocalDate returnDate;       // null if not returned

    private Integer renewalCount = 0;

    @Column(columnDefinition = "real default 0.0")
    private Double fineAmount = 0.0;

    private boolean finePaid = false;

    @Size(max = 500)
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "issued_by_id")
    private User issuedBy;              // Librarian / staff

    @ManyToOne
    @JoinColumn(name = "returned_by_id")
    private User returnedBy;

    // ...
}
