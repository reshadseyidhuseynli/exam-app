package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "examiner")
public class ExaminerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "pin", nullable = false, unique = true)
    private String pin;

    @OneToMany(mappedBy = "examiner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultEntity> results;

}
