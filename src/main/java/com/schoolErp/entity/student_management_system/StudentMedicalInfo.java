package com.schoolErp.entity.student_management_system;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_medical_info")
public class StudentMedicalInfo extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private Student student;

    @Column(columnDefinition = "text")
    private String allergies;

    @Column(columnDefinition = "text")
    private String chronicConditions;

    @Column(columnDefinition = "text")
    private String medications;

    @Column(columnDefinition = "text")
    private String disabilities;

    @Size(max = 15)
    private String bloodGroup;          // Can duplicate from student if needed

    @Size(max = 255)
    private String doctorName;

    @Size(max = 15)
    private String doctorContact;

    @Column(columnDefinition = "text")
    private String emergencyInstructions;

    private boolean needsSpecialAssistance = false;

    // ...
}
