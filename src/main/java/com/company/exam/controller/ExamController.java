package com.company.exam.controller;

import com.company.exam.dto.request.NewExamDetails;
import com.company.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public void addExam(NewExamDetails examDetails) {
        examService.addExam(examDetails);
    }
}
