package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.DriverStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "drivers")
public class Driver extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;                  // Core user (name, phone, etc.)

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle assignedVehicle;    // Primary vehicle

    @Size(max = 20)
    private String licenseNumber;

    private LocalDate licenseExpiry;

    @Enumerated(EnumType.STRING)
    private DriverStatus status = DriverStatus.ACTIVE;

    private boolean hasConductorAssistant = false;

    // ...
}
