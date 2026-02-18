package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "hostel_rooms",
        uniqueConstraints = @UniqueConstraint(columnNames = {"hostel_id", "room_number"}))
public class HostelRoom extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;

    @NotBlank
    @Size(max = 50)
    private String roomNumber;          // A-101, G-205, Annex-12

    @Enumerated(EnumType.STRING)
    private RoomType roomType;          // SINGLE, DOUBLE, TRIPLE, DORMITORY, AC, NON_AC

    private Integer capacity;           // max beds

    private Integer occupiedBeds = 0;   // calculated

    @Size(max = 20)
    private String floor;

    @Size(max = 100)
    private String wing;                // East Wing, New Block

    private boolean isAvailable = true;

    private boolean hasAttachedBathroom = false;

    private boolean isSpecialRoom = false; // e.g. prefect room, medical room

    @Column(columnDefinition = "jsonb")
    private String facilities;          // JSONB: ["fan", "study_table", "almirah", "wifi"]

    // ...
}
