package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.AcademicYear;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "transport_routes")
public class TransportRoute extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @NotBlank
    @Size(max = 100)
    private String routeName;           // "Route 1 - Noida Sec 62", "Morning Greater Noida West"

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private String shift;               // MORNING, AFTERNOON, FULL_DAY

    private Double monthlyFee;          // Base transport fee for this route

    @Column(columnDefinition = "jsonb")
    private String routePath;           // JSONB array of {lat, lng, name, sequence, distance_km, estimated_time_min}

    private Double totalDistanceKm;

    private Integer estimatedTimeMinutes;

    private boolean active = true;

    // For optimization (future)
    private String optimizationNotes;   // "Optimized 2025-12 via Google OR-Tools"

    // ...
}
