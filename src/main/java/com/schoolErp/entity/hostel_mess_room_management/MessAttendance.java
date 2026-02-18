package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.student_management_system.Student;
import com.schoolErp.enums.MealType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mess_attendances")
public class MessAttendance extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mess_att_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MessMenu menu;

    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private boolean present = false;

    private Double costDeducted;        // if mess is pay-per-meal

    // ...
}
