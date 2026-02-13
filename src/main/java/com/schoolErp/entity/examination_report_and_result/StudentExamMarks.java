package com.schoolErp.entity.examination_report_and_result;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.core.User;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_exam_marks",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "exam_schedule_id"}))
public class StudentExamMarks extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marks_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_schedule_id", nullable = false)
    private ExamSchedule examSchedule;

    private Integer theoryMarksObtained;

    private Integer practicalMarksObtained;

    private Integer totalMarksObtained; // Calculated: theory + practical

    private boolean isAbsent = false;

    @Size(max = 500)
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "marked_by_id")
    private User markedBy;              // Teacher

    private LocalDateTime markedAt;

    // ... business method: calculateTotal()
}
