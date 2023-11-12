package com.company.exam.repository;

import com.company.exam.entity.ExamDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDetailRepository extends JpaRepository<ExamDetailEntity, Long> {
}
