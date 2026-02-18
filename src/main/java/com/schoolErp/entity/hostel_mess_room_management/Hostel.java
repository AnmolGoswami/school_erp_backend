package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import com.schoolErp.entity.core.User;
import com.schoolErp.enums.HostelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "hostels")
public class Hostel extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hostel_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, updatable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 100)
    private String name;                // "Boys Hostel A", "Girls Hostel - New Block", "Senior Boys"

    @Size(max = 50)
    private String code;                // BHA, GNH, SB1

    @Enumerated(EnumType.STRING)
    private HostelType type;            // BOYS, GIRLS, JUNIOR, SENIOR, STAFF, MIXED

    private Integer totalRooms;

    private Integer totalBeds;

    @Size(max = 255)
    private String addressWithinCampus;

    @ManyToOne
    @JoinColumn(name = "warden_id")
    private User warden;                // Usually staff user

    private boolean active = true;

    // ...
}
