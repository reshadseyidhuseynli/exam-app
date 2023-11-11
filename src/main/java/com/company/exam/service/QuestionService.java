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
        examEntity.getQuestions().add(questionEntity);
        questionEntity.setExam(examEntity);

        Integer maxNumber = questionRepository.findMaxNumberByExamId(examEntity.getId());
        questionEntity.setNumber(maxNumber + 1);

        questionRepository.save(questionEntity);
        log.info("New question added for exam: {}", examTitle);
    }

    public QuestionResponseDto getRandomQuestion(Long examId, List<Integer> numbers) {
        List<QuestionDto> questionEntities = getByExamIdAndNumberIn(examId, numbers)
                .stream()
                .map(questionMapper::toQuestionDto)
                .collect(Collectors.toList());
        return QuestionResponseDto.of(questionEntities);
    }

    public List<QuestionEntity> getByExamIdAndNumberIn(Long examId, List<Integer> numbers) {
        return questionRepository.findByExamIdAndNumberIn(examId, numbers);
    }

}
