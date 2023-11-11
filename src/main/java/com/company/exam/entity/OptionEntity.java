package com.company.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_option")
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "option_text", nullable = false)
    private String text;

    @Column(name = "right_answer", nullable = false)
    private boolean isRightAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionEntity question;

}
