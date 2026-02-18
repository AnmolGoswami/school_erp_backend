package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "vehicles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "registration_number"}))
public class Vehicle extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veh_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true)
    private String registrationNumber;  // DL7C 1234

    @NotBlank
    @Size(max = 100)
    private String name;                // "Bus 1 - Blue", "Van A - Morning Shift"

    @Size(max = 50)
    private String makeModel;           // Tata Starbus, Mahindra Bolero

    private Integer seatingCapacity;

    private Integer currentOccupancy = 0; // calculated

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType = VehicleType.BUS;

    @Size(max = 20)
    private String gpsDeviceId;         // IMEI or unique tracker ID

    private boolean active = true;

    private LocalDate insuranceExpiry;

    private LocalDate pollutionCertificateExpiry;

    private LocalDate fitnessCertificateExpiry;

    @Column(columnDefinition = "jsonb")
    private String documents;           // JSONB paths to RC, Insurance, Permit PDFs

    // ...
}
