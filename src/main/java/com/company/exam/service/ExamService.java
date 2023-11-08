package com.company.exam.service;

import com.company.exam.dto.request.NewExamDetails;
import com.company.exam.entity.Exam;
import com.company.exam.entity.ExamDetails;
import com.company.exam.entity.ExamGroup;
import com.company.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamGroupService examGroupService;

    public void addExam(NewExamDetails request) {
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
}
