package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam_group")
public class ExamGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

}
