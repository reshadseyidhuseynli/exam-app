package com.company.exam.repository;

import com.company.exam.entity.ExamGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamGroupRepository extends JpaRepository<ExamGroupEntity, Long> {

    Optional<ExamGroupEntity> findByName(String name);

    boolean existsByName(String name);

}
