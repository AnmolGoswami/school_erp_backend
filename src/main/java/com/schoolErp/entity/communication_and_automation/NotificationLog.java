package com.schoolErp.entity.communication_and_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.enums.NotificationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notification_logs")
public class NotificationLog extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outbound_notification_id", nullable = false)
    private OutboundNotification notification;

    @Enumerated(EnumType.STRING)
    private NotificationStatus statusAtTime;

    @Column(columnDefinition = "text")
    private String responseFromProvider;    // JSON or text from API

    @Size(max = 500)
    private String errorDetails;

    // ...
}
