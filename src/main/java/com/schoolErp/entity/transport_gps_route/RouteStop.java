package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
@Table(name = "route_stops")
public class RouteStop extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stop_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private TransportRoute route;

    @NotBlank
    @Size(max = 150)
    private String stopName;            // "Sec 62 Market", "Gaur City 7"

    private Integer sequenceOrder;      // 1 = first pickup, last = school

    private Double latitude;

    private Double longitude;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Double distanceFromPreviousKm;

    private Integer maxStudents;

    private boolean isPickupOnly = false;

    private boolean isDropOnly = false;

    // ...
}
