package com.company.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private ExamEntity exam;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionEntity> options;

}
