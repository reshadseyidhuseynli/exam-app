package com.company.exam.repository;

import com.company.exam.entity.Question;
import com.company.exam.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {

    Optional<List<QuestionOption>> findByQuestion(Question question);

}
