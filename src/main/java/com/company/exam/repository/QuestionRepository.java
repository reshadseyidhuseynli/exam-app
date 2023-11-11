package com.company.exam.repository;

import com.company.exam.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(value = "SELECT MAX(e.number) FROM QuestionEntity e WHERE e.exam.id = :examId")
    Integer findMaxNumberByExamId(Long examId);

    List<QuestionEntity> findByExamIdAndNumberIn(Long examId, List<Integer> numbers);

}