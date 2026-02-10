package com.schoolErp.entity.core;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// 5. User (Staff, Admin, Teacher, later Student/Parent as separate or linked)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "username"}),
                @UniqueConstraint(columnNames = {"tenant_id", "email"})
        })
public class User extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String username;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String passwordHash;        // BCrypt encoded

    @NotBlank
    @Size(max = 100)
    private String fullName;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 15)
    private String phone;

    @Column(columnDefinition = "date")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean enabled = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private LocalDateTime lastLogin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // For session control / refresh tokens
    @Column(columnDefinition = "text")
    private String refreshToken;

    private LocalDateTime refreshTokenExpiry;

    // ...
}
