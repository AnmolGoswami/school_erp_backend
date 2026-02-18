package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "hostel_beds")
public class HostelBed extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bed_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private HostelRoom room;

    @NotBlank
    @Size(max = 50)
    private String bedNumber;           // A-101-1, G-205-B

    @Enumerated(EnumType.STRING)
    private BedStatus status = BedStatus.VACANT;

    private Integer sequenceInRoom;     // 1 = lower bunk, 2 = upper, etc.

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student currentOccupant;    // null if vacant

    private LocalDate allocationDate;

    private LocalDate expectedVacancyDate;

    // ...
}
