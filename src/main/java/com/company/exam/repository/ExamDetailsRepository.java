package com.company.exam.repository;

import com.company.exam.entity.ExamDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamDetailsRepository extends JpaRepository<ExamDetails, Integer> {
}
