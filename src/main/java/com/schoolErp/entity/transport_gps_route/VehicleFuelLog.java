package com.schoolErp.entity.transport_gps_route;

import com.schoolErp.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "vehicle_fuel_logs")
public class VehicleFuelLog extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fuel_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private LocalDate fillDate;

    private Double litresFilled;

    private Double amountPaid;

    private Double odometerReading;

    private Double mileageKmPerLitre;   // calculated

    @Size(max = 500)
    private String remarks;

    // ...
}
