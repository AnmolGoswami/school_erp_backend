package com.schoolErp.entity.hostel_mess_room_management;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.Tenant;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mess_menus")
public class MessMenu extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;              // or null = common mess

    private LocalDate menuDate;

    @Enumerated(EnumType.STRING)
    private MealType mealType;          // BREAKFAST, LUNCH, SNACKS, DINNER

    @Column(columnDefinition = "text")
    private String items;               // "Aloo Paratha, Curd, Pickle" or JSONB for structured

    private Integer estimatedStudents;

    private Double estimatedCostPerMeal;

    // ...
}
