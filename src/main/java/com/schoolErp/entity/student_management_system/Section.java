package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sections",
        uniqueConstraints = @UniqueConstraint(columnNames = {"school_class_id", "name"}))
public class Section extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false)
    private String name;                // "A", "B", "C", "Commerce", "Girls"

    @ManyToOne
    @JoinColumn(name = "class_teacher_id")
    private User classTeacher;          // Usually a teacher user

    @Column(columnDefinition = "integer default 0")
    private Integer maxCapacity;

    private boolean active = true;

    // ...
}
