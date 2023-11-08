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
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "exam_details_id", referencedColumnName = "id")
    private ExamDetails examDetails;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "exam")
    private List<Question> questions;

}
