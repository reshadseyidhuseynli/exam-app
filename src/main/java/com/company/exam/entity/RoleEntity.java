package com.company.exam.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false, unique = true)
    private Integer role;

    @Column(name = "value", nullable = false, unique = true)
    private String value;

}
