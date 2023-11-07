package com.company.exam.repository;

import com.company.exam.entity.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {
}
