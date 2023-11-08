package com.company.exam.repository;

import com.company.exam.entity.ExamGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamGroupRepository extends JpaRepository<ExamGroup, Integer> {

    boolean existsByName(String groupName);

    Optional<ExamGroup> findByName(String groupName);
}
