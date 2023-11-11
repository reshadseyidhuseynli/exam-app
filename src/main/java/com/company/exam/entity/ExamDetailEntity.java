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
@Table(name = "exam_detail")
public class ExamDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "question_count", nullable = false)
    private Integer questionCount;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private ExamGroupEntity group;

}
