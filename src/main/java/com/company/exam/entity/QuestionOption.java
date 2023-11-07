package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "question_option")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
    @Column(name = "option_text")
    private String optionText;
    @Column(name = "right_answer")
    private boolean isRightAnswer;

}
