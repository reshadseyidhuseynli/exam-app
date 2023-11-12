package com.company.exam.repository;

import com.company.exam.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(value = "SELECT MAX(e.number) FROM QuestionEntity e WHERE e.exam.id = :examId")
    Integer findMaxNumberByExamId(Long examId);

    List<QuestionEntity> findByExamIdAndNumberIn(Long examId, List<Integer> numbers);

    @Query(value = "SELECT * FROM question WHERE exam_id = :examId ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<QuestionEntity> findRandomQuestionsByExamId(@Param("examId") Long examId, @Param("limit") int limit);
}