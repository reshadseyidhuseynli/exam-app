package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "id")
    private List<QuestionOption> questionOptions;
}
