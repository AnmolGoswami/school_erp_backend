package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import com.schoolErp.enums.GuardianType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guardians")
public class Guardian extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guardian_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    private GuardianType type;          // FATHER, MOTHER, GUARDIAN, LOCAL_GUARDIAN

    @NotBlank
    @Size(max = 150)
    private String fullName;

    @Size(max = 15)
    private String mobile;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 50)
    private String occupation;

    @Size(max = 100)
    private String qualification;

    @Column(columnDefinition = "text")
    private String address;

    private boolean isPrimaryContact = false;

    private boolean receivesSMS = true;

    private boolean receivesEmail = true;

    private boolean isEmergencyContact = false;

    // ...
}