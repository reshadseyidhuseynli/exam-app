package com.company.exam.service;

import com.company.exam.dto.ExamDetailsInfo;
import com.company.exam.entity.Exam;
import com.company.exam.entity.ExamDetails;
import com.company.exam.entity.ExamGroup;
import com.company.exam.error.exception.NotFoundException;
import com.company.exam.mapper.ExamDetailsMapper;
import com.company.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamGroupService examGroupService;
    private final ExamDetailsMapper examDetailsMapper;

    public void addExam(ExamDetailsInfo request) {
        ExamGroup examGroup = examGroupService.findByNameOrAddIfNotExist(request.getGroupName());
        String examName = request.getExamName();
        String duration = request.getDuration();
        Integer questionCount = request.getQuestionCount();

        ExamDetails examDetails = ExamDetails.builder()
                .group(examGroup)
                .name(examName)
                .duration(duration)
                .questionCount(questionCount)
                .build();

        Exam exam = Exam.builder()
                .examDetails(examDetails)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .build();

        examRepository.save(exam);
    }

    public ExamDetailsInfo getById(Integer id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found exam by id: " + id));
        return examDetailsMapper.toDto(exam.getExamDetails());
    }
}
