package com.company.exam.repository;

import com.company.exam.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

    Optional<ExamEntity> findByTitle(String title);

    void deleteByTitle(String title);

}
