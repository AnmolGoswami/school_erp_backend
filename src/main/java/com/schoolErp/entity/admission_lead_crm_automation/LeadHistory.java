package com.schoolErp.entity.admission_lead_crm_automation;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 2. LeadHistory / Stage Change Log (audit trail for funnel movement)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lead_histories")
public class LeadHistory extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lead_hist_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadStatus toStatus;

    @ManyToOne
    @JoinColumn(name = "changed_by_id")
    private User changedBy;

    @Column(columnDefinition = "text")
    private String remarks;

    private LocalDateTime transitionTime = LocalDateTime.now();

    // ...
}
