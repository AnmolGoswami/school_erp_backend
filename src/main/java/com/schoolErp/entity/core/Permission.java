package com.schoolErp.entity.core;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 4. Permission (Fine-grained matrix)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perm_seq")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String code;                // "student:read", "fee:collect", "attendance:mark", "exam:publish" etc.

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 500)
    private String description;

    // Many permissions are global, no tenant_id needed here
}
