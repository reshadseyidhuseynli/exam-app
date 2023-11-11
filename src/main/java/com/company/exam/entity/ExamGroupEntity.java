package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam_group")
public class ExamGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
