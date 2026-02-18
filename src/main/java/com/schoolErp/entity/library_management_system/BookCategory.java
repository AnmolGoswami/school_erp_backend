package com.schoolErp.entity.library_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "book_categories")
public class BookCategory extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_cat_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 100)
    private String name;                // Fiction, Science, Reference, CBSE Textbooks, Magazines, Comics

    @Size(max = 255)
    private String description;

    private boolean active = true;

    // ...
}
