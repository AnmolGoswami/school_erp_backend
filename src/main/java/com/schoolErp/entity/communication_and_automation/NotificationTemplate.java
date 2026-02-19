package com.schoolErp.entity.communication_and_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.CommunicationChannel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notification_templates",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "code"}))
public class NotificationTemplate extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temp_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String code;                // FEE_REMINDER_3_DAYS, ATTENDANCE_ABSENT, EXAM_RESULT_PUBLISHED, BIRTHDAY_WISH

    @NotBlank
    @Size(max = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    private CommunicationChannel primaryChannel;

    @Column(columnDefinition = "text")
    private String content;             // Message with placeholders e.g. "Dear {guardian_name}, your ward {student_name} was absent today."

    @Column(columnDefinition = "text")
    private String whatsappTemplateName; // Exact name registered in WhatsApp Business API

    @Column(columnDefinition = "text")
    private String emailSubject;

    private boolean active = true;

    private boolean requiresApproval = false; // for important messages

    @Column(columnDefinition = "jsonb")
    private String placeholders;        // ["student_name", "guardian_name", "amount_due", "due_date"]

    // ...
}
