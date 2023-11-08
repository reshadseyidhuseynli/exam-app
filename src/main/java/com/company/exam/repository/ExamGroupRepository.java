package com.company.exam.repository;

import com.company.exam.entity.ExamGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamGroupRepository extends JpaRepository<ExamGroup, Integer> {
}
