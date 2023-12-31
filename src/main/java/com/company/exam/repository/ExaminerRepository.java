package com.company.exam.repository;

import com.company.exam.entity.ExaminerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExaminerRepository extends JpaRepository<ExaminerEntity, Long> {

    Optional<ExaminerEntity> findByPin(String pin);

}
