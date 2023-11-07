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
    @Column(name = "name")
    private String name;
    @Column(name = "group")
    private String group;
    @Column(name = "duration")
    private String duration;
    @Column(name = "question_count")
    private String questionCount;

}
