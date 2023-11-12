package com.company.exam.service;

import com.company.exam.dto.ExamDto;
import com.company.exam.dto.QuestionDto;
import com.company.exam.dto.request.AnswerRequestDto;
import com.company.exam.dto.request.CreateExamRequestDto;
import com.company.exam.dto.request.EnterRequestDto;
import com.company.exam.dto.request.UpdateExamRequestDto;
import com.company.exam.dto.response.ExamResponseDto;
import com.company.exam.dto.response.QuestionResponseDto;
import com.company.exam.dto.response.ResultResponseDto;
import com.company.exam.entity.*;
import com.company.exam.error.exception.DuplicateExamTitleException;
import com.company.exam.error.exception.InternalServiceException;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.ExamMapper;
import com.company.exam.mapper.QuestionMapper;
import com.company.exam.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final ExamDetailRepository examDetailRepository;
    private final ExamGroupRepository examGroupRepository;
    private final QuestionService questionService;
    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public ExamDto getByTitle(String title) {
        return examRepository.findByTitle(title)
                .map(examMapper::toExamDto)
                .orElseThrow(() -> new NotFoundException("Not found exam with given title: " + title));
    }

    public ExamResponseDto getAll() {
        List<ExamDto> examDtoList = examRepository.findAll().stream()
                .map(examMapper::toExamDto)
                .collect(Collectors.toList());
        return ExamResponseDto.of(examDtoList);
    }

    public void addExam(CreateExamRequestDto requestDto) {
        String title = requestDto.getTitle();
        Optional<ExamEntity> optionalExamEntity = examRepository.findByTitle(title);

        if (optionalExamEntity.isPresent()) {
            throw new DuplicateExamTitleException(
                    "There is already have an active exam with given title: " + title);
        }

        ExamEntity examEntity = examMapper.toExamEntity(requestDto);
        examEntity.setCreatedDate(LocalDate.now());
        examEntity.setUpdatedDate(LocalDate.now());
        String groupName = examEntity.getExamDetail().getGroup().getName();
        if (examGroupRepository.existsByName(groupName)) {
            ExamGroupEntity existGroup = examGroupRepository.findByName(groupName)
                    .orElseThrow(() -> new NotFoundException(
                            "Not found exam group with given name: " + groupName));
            examEntity.getExamDetail().setGroup(existGroup);
        } else {
            examGroupRepository.save(examEntity.getExamDetail().getGroup());
        }
        examDetailRepository.save(examEntity.getExamDetail());
        examRepository.save(examEntity);
        log.info("Exam added to db with title: {}", requestDto.getTitle());
    }


    public void updateExam(String title, UpdateExamRequestDto requestDto) {
        Optional<ExamEntity> optionalExamEntity = examRepository.findByTitle(title);

        if (!optionalExamEntity.isPresent()) {
            throw new NotFoundException("Not found exam with given title: " + title);
        }

        String newTitle = requestDto.getTitle();
        if (!title.equals(newTitle)) {
            Optional<ExamEntity> optionalExamEntityWithNewTitle =
                    examRepository.findByTitle(newTitle);

            if (optionalExamEntityWithNewTitle.isPresent()) {
                throw new DuplicateExamTitleException(
                        "There is already have an active exam with given title: " + title);
            }
        }

        ExamEntity examEntity = optionalExamEntity.get();
        examMapper.updateExamEntity(examEntity, requestDto);
        examEntity.setUpdatedDate(LocalDate.now());

        examRepository.save(examEntity);
        log.info("Exam updated with title: {} to new title: {}", title, newTitle);
    }

    public void deleteByTitle(String title) {
        ExamEntity examEntity = examRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Not found exam with given title: " + title));
        examRepository.delete(examEntity);
        log.info("Exam removed with title: {}", title);
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

        List<QuestionDto> questionDtoList = questionRepository
                .findRandomQuestionsByExamId(examEntity.getId(), questionCount)
                .stream()
                .map(questionMapper::toQuestionDto)
                .collect(Collectors.toList());
        return QuestionResponseDto.of(questionDtoList);
    }

    public ResultResponseDto finishExam(AnswerRequestDto requestDto) {
        ResultEntity resultEntity = resultRepository.findByEntranceCode(requestDto.getEntranceCode())
                .orElseThrow(() -> new NotFoundException(
                        "Not found result with given entranceCode: " + requestDto.getEntranceCode()));

        List<AnswerRequestDto.Answer> answers = requestDto.getAnswers();

        List<Integer> answeredQuestionNumbers = answers.stream()
                .map(AnswerRequestDto.Answer::getQuestionNumber)
                .collect(Collectors.toList());

        List<QuestionEntity> answeredQuestions = questionRepository
                .findByExamIdAndNumberIn(resultEntity.getExam().getId(), answeredQuestionNumbers);

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
