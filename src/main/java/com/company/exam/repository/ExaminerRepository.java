package com.company.exam.repository;

import com.company.exam.entity.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {

    Optional<Examiner> findByPin(String pin);
}
