package com.company.exam.service;

import com.company.exam.dto.ExamDto;
import com.company.exam.dto.request.CreateExamRequestDto;
import com.company.exam.dto.request.UpdateExamRequestDto;
import com.company.exam.dto.response.ExamResponseDto;
import com.company.exam.entity.ExamEntity;
import com.company.exam.error.exception.DuplicateExamTitleException;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.ExamMapper;
import com.company.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

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

        examRepository.save(examEntity);
        log.info("Exam updated with title: {} to new title: {}", title, newTitle);
    }

    public void deleteByTitle(String title) {
        examRepository.deleteByTitle(title);
        log.info("Exam removed with title: {}", title);
    }

}
