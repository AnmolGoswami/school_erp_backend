package com.schoolErp.entity.examination_report_and_result;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.student_management_system.SchoolClass;
import com.schoolErp.entity.student_management_system.Section;
import jakarta.persistence.*;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "exam_schedules",
        uniqueConstraints = @UniqueConstraint(columnNames = {"exam_id", "school_class_id", "subject_id"}))
public class ExamSchedule extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_sch_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_class_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;            // Optional if whole class

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(columnDefinition = "date")
    private LocalDate examDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer maxMarksTheory;

    private Integer maxMarksPractical;

    private Integer passingMarks;

    // ...
}
