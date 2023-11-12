package com.company.exam.service;

import com.company.exam.dto.QuestionDto;
import com.company.exam.dto.request.CreateQuestionRequestDto;
import com.company.exam.dto.response.QuestionResponseDto;
import com.company.exam.entity.ExamEntity;
import com.company.exam.entity.QuestionEntity;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.QuestionMapper;
import com.company.exam.repository.ExamRepository;
import com.company.exam.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ExamRepository examRepository;

    public void add(String examTitle, CreateQuestionRequestDto requestDto) {
        ExamEntity examEntity = examRepository.findByTitle(examTitle)
                .orElseThrow(() -> new NotFoundException("Exam not found with given title!"));

        QuestionEntity questionEntity = questionMapper.toQuestionEntity(requestDto);
        questionEntity.setExam(examEntity);

        if (examEntity.getQuestions().isEmpty()) {
            questionEntity.setNumber(1);
        } else {
            Integer maxNumber = questionRepository.findMaxNumberByExamId(examEntity.getId());
            questionEntity.setNumber(maxNumber + 1);
        }
        questionEntity.getOptions().forEach(optionEntity -> {
            optionEntity.setQuestion(questionEntity);
        });

        examEntity.getQuestions().add(questionEntity);

        questionRepository.save(questionEntity);
        log.info("New question added for exam: {}", examTitle);
    }

}
