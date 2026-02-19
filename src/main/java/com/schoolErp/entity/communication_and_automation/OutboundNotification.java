package com.schoolErp.entity.communication_and_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Guardian;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.CommunicationChannel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbound_notifications",
        indexes = {
                @Index(columnList = "status, scheduled_at"),
                @Index(columnList = "student_id, event_type_id")
        })
public class OutboundNotification extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notif_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private NotificationRule rule;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private NotificationTemplate template;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "guardian_id")
    private Guardian guardian;

    @ManyToOne
    @JoinColumn(name = "user_id")           // if sent to staff
    private User user;

    @Enumerated(EnumType.STRING)
    private CommunicationChannel channel;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status = NotificationStatus.QUEUED;

    @NotNull
    private LocalDateTime scheduledAt;

    private LocalDateTime sentAt;

    private LocalDateTime deliveredAt;

    private LocalDateTime readAt;           // WhatsApp read receipt if available

    @Column(columnDefinition = "text")
    private String renderedContent;         // Final message after placeholder replacement

    @Column(columnDefinition = "text")
    private String errorMessage;

    @Size(max = 100)
    private String messageId;               // Provider reference (WhatsApp msg id, SMS uuid)

    private Integer retryCount = 0;

    private Integer maxRetries = 3;

    // Link to triggering entity (optional)
    private String relatedEntityType;       // "FeeDue", "Attendance", "ExamResult"

    private Long relatedEntityId;

    // ...
}
