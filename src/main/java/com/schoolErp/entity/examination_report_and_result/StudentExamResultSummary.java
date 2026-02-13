package com.schoolErp.entity.examination_report_and_result;

import com.schoolErp.AuditableEntity;
import com.schoolErp.entity.student_management_system.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student_exam_result_summaries",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "exam_id"}))
public class StudentExamResultSummary extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_sum_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    private Integer totalMarksObtained;

    private Integer totalMaxMarks;

    @Column(columnDefinition = "real")
    private Double percentage;

    @Size(max = 10)
    private String grade;               // A1, B2, etc. – from grading scheme

    private Integer classRank;

    private Integer sectionRank;

    private Integer overallRank;        // School-wide if needed

    private boolean passed = true;

    @Column(columnDefinition = "jsonb")
    private String subjectWiseDetails;  // JSONB for quick view: subject → marks/grade

    private boolean isPublished = false;

    // ...
}
