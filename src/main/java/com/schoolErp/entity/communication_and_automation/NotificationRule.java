package com.schoolErp.entity.communication_and_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;

@Entity
@Table(name = "notification_rules")
public class NotificationRule extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    private NotificationEventType eventType;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private NotificationTemplate template;

    private boolean enabled = true;

    @Column(columnDefinition = "integer default 0")
    private Integer delayDays;          // e.g. fee reminder 3 days before due

    @Enumerated(EnumType.STRING)
    private ReminderFrequency frequency = ReminderFrequency.ONCE;

    @Column(columnDefinition = "jsonb")
    private String conditions;          // JSONB e.g. {"fee_due_amount > 500", "class in [10,12]"}

    private boolean sendToGuardian = true;

    private boolean sendToStudent = false;

    private boolean sendToAllGuardians = false; // if multiple guardians

    private boolean sendEmailCopy = true;

    private boolean sendSmsFallback = true;     // if WhatsApp fails

    // ...
}
