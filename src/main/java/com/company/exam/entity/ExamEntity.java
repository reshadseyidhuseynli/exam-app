package com.company.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exam")
public class ExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "exam_details_id", referencedColumnName = "id")
    private ExamDetailEntity examDetail;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions;

}
