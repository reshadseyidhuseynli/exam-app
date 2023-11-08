package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam_details")
public class ExamDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private ExamGroup group;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private String duration;
    @Column(name = "question_count")
    private String questionCount;

}
