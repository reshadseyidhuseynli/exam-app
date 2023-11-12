package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "result")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entrance_code", unique = true)
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

    @ManyToOne
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private ExaminerEntity examiner;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private ExamEntity exam;

}
