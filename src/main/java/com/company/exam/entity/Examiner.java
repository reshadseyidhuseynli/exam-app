package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "examiner")
public class Examiner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "pin")
    private String pin;

    @OneToMany(mappedBy = "id")
    private List<ExaminerResult> examinerResults;

}
