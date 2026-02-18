package com.schoolErp.entity.library_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.enums.CopyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "book_copies")
public class BookCopy extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "copy_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String accessionNumber;     // Unique per copy, e.g. LIB-2025-0001

    @Size(max = 100)
    private String barcode;             // Can be same as book or unique

    @Size(max = 100)
    private String rfidTag;

    @Enumerated(EnumType.STRING)
    private CopyStatus status = CopyStatus.AVAILABLE;

    @Size(max = 500)
    private String location;            // Shelf No, Section, etc.

    private boolean damaged = false;

    private LocalDate addedDate;

    // ...
}
