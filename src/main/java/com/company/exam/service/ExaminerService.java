package com.company.exam.service;

import com.company.exam.dto.request.AnswerRequestDto;
import com.company.exam.dto.request.CreateExaminerRequestDto;
import com.company.exam.dto.request.EnterRequestDto;
import com.company.exam.dto.response.QuestionResponseDto;
import com.company.exam.dto.response.ResultResponseDto;
import com.company.exam.entity.*;
import com.company.exam.error.exception.InternalServiceException;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.ExaminerMapper;
import com.company.exam.repository.ExamRepository;
import com.company.exam.repository.ExaminerRepository;
import com.company.exam.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExaminerService {

    private final ExaminerRepository examinerRepository;
    private final ExaminerMapper examinerMapper;
    private final ExamRepository examRepository;
    private final ResultRepository resultRepository;
    private final QuestionService questionService;

    public void add(CreateExaminerRequestDto requestDto) {
        ExaminerEntity examinerEntity = examinerMapper.toExaminerEntity(requestDto);
        examinerRepository.save(examinerEntity);
    }

    public void enter(EnterRequestDto requestDto) {
        ExaminerEntity examinerEntity = examinerRepository.findByPin(requestDto.getPin())
                .orElseThrow(() -> new NotFoundException(
                        "Not found examiner by given pin: " + requestDto.getPin()));

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setEntranceCode(requestDto.getEntranceCode());
        resultEntity.setExaminer(examinerEntity);

        examinerEntity.getResults().add(resultEntity);
        examinerRepository.save(examinerEntity);
    }

    public QuestionResponseDto startExam(String examTitle, EnterRequestDto requestDto) {
        ExamEntity examEntity = examRepository.findByTitle(examTitle)
                .orElseThrow(() -> new NotFoundException("Not found exam with given title: " + examTitle));
        Integer questionCount = examEntity.getExamDetail().getQuestionCount();

        ResultEntity resultEntity = resultRepository.findByEntranceCode(requestDto.getEntranceCode())
                .orElseThrow(() -> new NotFoundException(
                        "Not found result with given entranceCode: " + requestDto.getEntranceCode()));
        resultEntity.setExam(examEntity);
        resultEntity.setExamStartTime(LocalDateTime.now());

        resultRepository.save(resultEntity);

        Set<Integer> randomQuestionNumbers = new HashSet<>();
        Random random = new Random();
        while (randomQuestionNumbers.size() < questionCount) {
            randomQuestionNumbers.add(random.nextInt());
        }

        return questionService.getRandomQuestion(examEntity.getId(), new ArrayList<>(randomQuestionNumbers));

    }

    public ResultResponseDto finishExam(AnswerRequestDto requestDto) {
        ResultEntity resultEntity = resultRepository.findByEntranceCode(requestDto.getEntranceCode())
                .orElseThrow(() -> new NotFoundException(
                        "Not found result with given entranceCode: " + requestDto.getEntranceCode()));

        List<AnswerRequestDto.Answer> answers = requestDto.getAnswers();

        List<Integer> answeredQuestionNumbers = answers.stream()
                .map(AnswerRequestDto.Answer::getQuestionNumber)
                .collect(Collectors.toList());

        List<QuestionEntity> answeredQuestions = questionService
                .getByExamIdAndNumberIn(resultEntity.getExam().getId(), answeredQuestionNumbers);

        List<Integer> correctOptionNumbers = answeredQuestions.stream()
                .map(questionEntity -> {
                    OptionEntity correctOption = questionEntity.getOptions().stream()
                            .filter(OptionEntity::isRightAnswer)
                            .findFirst()
                            .orElseThrow(() -> new InternalServiceException("Internal Service Error"));
                    return correctOption.getNumber();
                })
                .collect(Collectors.toList());

        int correctAnswerCount = 0;
        int wrongAnswerCount = 0;
        int nonAnsweredCount = resultEntity.getExam().getExamDetail().getQuestionCount() - answers.size();
        for (int i = 0; i < answers.size(); i++) {
            if (Objects.equals(answers.get(i).getSelectedOptionNumber(), correctOptionNumbers.get(i))) {
                correctAnswerCount++;
            } else {
                wrongAnswerCount++;
            }
        }
        resultEntity.setCorrectAnswerCount(correctAnswerCount);
        resultEntity.setWrongAnswerCount(wrongAnswerCount);
        resultEntity.setNonAnswerCount(nonAnsweredCount);
        resultEntity.setExamEndTime(LocalDateTime.now());
        resultRepository.save(resultEntity);
        return ResultResponseDto.of(correctAnswerCount, wrongAnswerCount, nonAnsweredCount);
    }

}
