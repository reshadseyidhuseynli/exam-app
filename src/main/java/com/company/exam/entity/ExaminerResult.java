package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "examiner_result")
public class ExaminerResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examiner;
    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;
    @Column(name = "entrance_code")
    private String entranceCode;
    @Column(name = "correct_answer_count")
    private Integer correctAnswerCount;
    @Column(name = "wrong_answer_count")
    private Integer wrongAnswerCount;
    @Column(name = "non_answer_count")
    private Integer nonAnswerCount;
    @Column(name = "exam_start_time")
    private LocalDateTime examStartTime;
    @Column(name = "exam_end_time")
    private LocalDateTime examEndTime;

}
