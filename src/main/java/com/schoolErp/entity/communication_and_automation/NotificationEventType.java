package com.schoolErp.entity.communication_and_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notification_event_types")
public class NotificationEventType extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_type_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 100)
    private String code;                // FEE_DUE_REMINDER, STUDENT_ABSENT, RESULT_PUBLISHED, BIRTHDAY, EMERGENCY, EVENT_ANNOUNCEMENT

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 500)
    private String description;

    private boolean isSystemEvent = true; // cannot delete

    // ...
}
