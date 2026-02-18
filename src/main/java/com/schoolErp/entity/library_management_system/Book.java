package com.schoolErp.entity.library_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books",
        indexes = {
                @Index(columnList = "isbn"),
                @Index(columnList = "barcode")
        })
public class Book extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BookCategory category;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 150)
    private String author;

    @Size(max = 100)
    private String publisher;

    @Size(max = 20)
    private String isbn;

    @Size(max = 50)
    private String edition;

    @Column(columnDefinition = "integer default 1")
    private Integer publicationYear;

    private Integer totalCopies;

    @Column(columnDefinition = "integer default 0")
    private Integer availableCopies;

    private Double price;

    @Size(max = 100)
    private String barcode;             // Primary for scanning

    @Size(max = 100)
    private String rfidTag;             // Optional for advanced setups

    @Column(columnDefinition = "text")
    private String description;

    @Size(max = 500)
    private String coverImagePath;      // File path or URL

    private boolean active = true;

    @Column(columnDefinition = "jsonb")
    private String customFields;        // e.g. {"class_level": "6-8", "language": "English"}

    // ... methods: isAvailable(), increment/decrement copies
}
